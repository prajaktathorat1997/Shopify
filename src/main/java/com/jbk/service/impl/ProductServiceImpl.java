package com.jbk.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exception.ResourceAlreadyExistException;
import com.jbk.exception.ResourceNotExistsException;
import com.jbk.exception.SomethingWentWrongException;
import com.jbk.model.CategoryModel;
import com.jbk.model.ProductModel;
import com.jbk.model.SupplierModel;
import com.jbk.service.ProductService;
import com.jbk.validation.ObjectValidation;

@Component
public class ProductServiceImpl implements ProductService {

	// ProductDao dao=new ProductDaoImpl();
	@Autowired
	private ProductDao dao;
	
	@Autowired
	private ObjectValidation  validation;

	@Autowired
	private ModelMapper mapper;
	
	Map<String, String> errorMap=new HashMap<String, String>();
	Map<Integer, Map<String, String>> rowErrorMap= new HashMap<>();
	
	Map<String, Map<Integer, Map<String, String>>> badRows=new HashMap<>();
	
	LinkedHashMap<String, Object> finalMap=new LinkedHashMap<String, Object>();

	int totalSheetRecord = 0;
	int totalExistsRecordCount=0;
	List<Integer> existsRowNumbers=new ArrayList<Integer>();

	@Override
	public int addProduct(ProductEntity product) {

		String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		product.setProductId(Long.parseLong(productId));

		return dao.addProduct(product);

	}

	@Override
	public ProductEntity getProductById(long productId) {

		return dao.getProductById(productId);
	}

	@Override
	public List<ProductEntity> getAllProducts() {

		return dao.getAllProducts();
	}

	@Override
	public int deleteProduct(long productId) {

		return dao.deleteProduct(productId);

	}

	@Override
	public int updateProduct(ProductEntity product) {
		return dao.updateProduct(product);

	}

	@Override
	public ProductEntity getProductByName(String productName) {
		ProductEntity productEntity = dao.getProductByName(productName);
		if (productEntity != null) {
			return productEntity;
		} else {
			throw new ResourceNotExistsException("Product not exists with name " + productName);
		}
	}

	@Override
	public List<ProductEntity> getProductByPriceRange(double minPrice, double maxPrice) {
		return dao.getProductByPriceRange(minPrice, maxPrice);
	}

	@Override
	public List<ProductEntity> getAllProductStartWith(String expression) {
		return dao.getAllProductStartWith(expression);
	}

	@Override
	public List<ProductEntity> sortProducts(String orderType, String field) {
		return dao.sortProducts(orderType, field);
	}

	@Override
	public double getMaxPrice() {

		double maxPrice = dao.getMaxPrice();
		if (maxPrice > 0) {
			return maxPrice;
		} else {
			throw new ResourceNotExistsException("Product not exists");

		}

	}

	@Override
	public List<ProductEntity> getMaxPriceProduct() {
		return dao.getMaxPriceProduct();
	}

	@Override
	public List<ProductModel> hqlEx() {
		List<ProductEntity> products = dao.hqlEx();

		List<ProductModel> listProductModel = new ArrayList<>();

		if (!products.isEmpty()) {
			for (ProductEntity productEntity : products) {
				ProductModel productModel = mapper.map(productEntity, ProductModel.class);
				listProductModel.add(productModel);
			}

		} else {

			throw new ResourceNotExistsException("Product not exists");

		}
		return listProductModel;
	}

	public List<ProductModel> readExcel(String filelocation) {
		List<ProductModel> productModelList = new ArrayList<>();
		try {
			FileInputStream fis = new FileInputStream(filelocation);
			Workbook workbook = WorkbookFactory.create(fis);

			Sheet sheet = workbook.getSheetAt(0);

			totalSheetRecord = sheet.getLastRowNum();

			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {
				Row row = (Row) rows.next();

				if (row.getRowNum() == 0) {
					continue;
				}
				int rowNum = row.getRowNum();
				ProductModel productModel = new ProductModel();

				String productId = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
				productModel.setProductId(Long.parseLong(productId + rowNum));

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {
					Cell cell = (Cell) cells.next();

					int columnIndex = cell.getColumnIndex();

					switch (columnIndex) {
					case 0: {
						productModel.setProductName(cell.getStringCellValue());
						break;
					}
					case 1: {
						SupplierModel supplierModel = new SupplierModel();
						supplierModel.setSupplierId((long) cell.getNumericCellValue());
						productModel.setSupplier(supplierModel);
						break;
					}
					case 2: {
						CategoryModel categoryModel = new CategoryModel();
						categoryModel.setCategoryId((long) cell.getNumericCellValue());

						productModel.setCategory(categoryModel);
						break;
					}

					case 3: {
						productModel.setProductQty((int) cell.getNumericCellValue());
						break;
					}
					case 4: {
						productModel.setProductPrice(cell.getNumericCellValue());
						break;
					}
					case 5: {
						Date inputDate = cell.getDateCellValue();
						// Define the input date format
						SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
						// Define the desired output format
						SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

						try {
							// Parse the input date string
							Date date = inputFormat.parse(inputDate.toString());
							// Format the date into the desired output format
							String mfgDate = outputFormat.format(date);
							// Output the formatted date

							productModel.setMfgDate(mfgDate);

							break;
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					}
					case 6: {
						Date inputDate = cell.getDateCellValue();
						// Define the input date format
						SimpleDateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
						// Define the desired output format
						SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

						try {
							// Parse the input date string
							Date date = inputFormat.parse(inputDate.toString());
							// Format the date into the desired output format
							String expdDate = outputFormat.format(date);
							// Output the formatted date

							productModel.setExpDate(expdDate);

							break;
						} catch (ParseException e) {
							e.printStackTrace();
						}
						break;
					}
					case 7: {
						productModel.setDeliveryCharges((int) cell.getNumericCellValue());
						break;
					}

					}

				}

				errorMap = validation.validateProduct(productModel);
				
				if(!errorMap.isEmpty()) {
					// error
					rowErrorMap.put(row.getRowNum()+1, errorMap);
				}else {
					// ok
					
					try {
						
						 ProductEntity dbProduct = getProductByName(productModel.getProductName());
						 if(dbProduct!=null) {
							 totalExistsRecordCount=totalExistsRecordCount+1;
							 existsRowNumbers.add(row.getRowNum()+1);
						 }
						
					} catch (ResourceNotExistsException e) {
						productModelList.add(productModel);
					}
				}
				
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while file reading");
		}

		return productModelList;

	}

	@Override
	public LinkedHashMap<String, Object> uploadSheet(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String msg = "";
		int uploadCount = 0;
		try {
			byte[] data = file.getBytes();

			FileOutputStream fos = new FileOutputStream("src/main/resources" + File.separator + fileName);
			fos.write(data);

			// read excel data
			List<ProductModel> productModelList = readExcel("src/main/resources" + File.separator + fileName);

			for (ProductModel productModel : productModelList) {

				ProductEntity productEntity = mapper.map(productModel, ProductEntity.class);
				int status = dao.addProduct(productEntity);
				uploadCount += status;
			}
			
			
			finalMap.put("Total Record In Sheet", totalSheetRecord);
			finalMap.put("Uploaded Record In Db", uploadCount);
			finalMap.put("Total Exists Record In DB", totalExistsRecordCount);
			finalMap.put("Already Exists Rows", existsRowNumbers);
			finalMap.put("Total Excluded", rowErrorMap.size());
			finalMap.put("Bad Record Row Number", rowErrorMap);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while file reading");
		}
		return finalMap;
	}
}
