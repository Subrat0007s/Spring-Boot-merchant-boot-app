package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStrcture;
import org.jsp.merchantbootapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStrcture<Product>> saveProduct(Product product, Integer m_id) {
		Optional<Merchant> dbMerchant = merchantDao.findByMerchantId(m_id);
		if (dbMerchant.isPresent()) {
			Merchant merchant = dbMerchant.get();
			product.setMerchant(merchant); // Assign Merchant to Product
			merchant.getProducts().add(product); // Assign Product to Merchant
			merchantDao.saveMerchant(merchant); // Update Merchant
			ResponseStrcture<Product> strcture = new ResponseStrcture<>();
			strcture.setMessage("Product Added");
			strcture.setData(productDao.Save(product));
			strcture.setStatus(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(strcture);
		}
		throw new ProductNotFoundException("Product can't be Saved. Invalid Merchant Id");
	}

	public ResponseEntity<ResponseStrcture<Product>> updateProduct(Product product) {
		ResponseStrcture<Product> strcture = new ResponseStrcture<>();
		Optional<Product> optional = productDao.findProductById(product.getId());
		if (optional.isPresent()) {
			Product products = optional.get();
			products.setName(product.getName());
			products.setBrand(product.getBrand());
			products.setCategory(product.getCategory());
			products.setDescription(product.getDescription());
			products.setImage_url(product.getImage_url());
			products.setCost(product.getCost());
			strcture.setMessage("Product details Updated");
			strcture.setStatus(HttpStatus.ACCEPTED.value());
			strcture.setData(productDao.Save(products));
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(strcture);
		}
		throw new ProductNotFoundException("Invalid Product Id , Product Details Failed to update");
	}

	public ResponseEntity<ResponseStrcture<Product>> findProductById(Integer id) {
		ResponseStrcture<Product> strcture = new ResponseStrcture<>();
		Optional<Product> optional = productDao.findProductById(id);
		if (optional.isPresent()) {
			strcture.setMessage("Id Found");
			strcture.setData(optional.get());
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new ProductNotFoundException("Invalid Id, Fetch Failed");
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findProductByBrand(String brand) {
		ResponseStrcture<List<Product>> strcture = new ResponseStrcture<>();
		List<Product> products = productDao.findByBrand(brand);
		strcture.setData(products);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Invalid Brand Name, Fetch Failed");
		}
		strcture.setMessage("List of products");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findProductByCategory(String category) {
		ResponseStrcture<List<Product>> strcture = new ResponseStrcture<>();
		List<Product> products = productDao.findByCategory(category);
		strcture.setData(products);
		if (products.isEmpty()) {
			throw new ProductNotFoundException("Invalid Category Name, Fetching the products Failed");
		}
		strcture.setMessage("List of Products");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<String>> deleteProduct(Integer id) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		Optional<Product> optional = productDao.findProductById(id);
		if (optional.isPresent()) {
			strcture.setData("Product Deleted");
			strcture.setMessage("Product Delete Successfully");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new ProductNotFoundException("Invalid Product Id!!");
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findAll() {
		ResponseStrcture<List<Product>> structure = new ResponseStrcture<>();
		List<Product> products = productDao.findAll();
		structure.setData(products);
		structure.setMessage("List of All Products");
		structure.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStrcture<Product>> findById(int id) {
		Optional<Product> recProduct = productDao.findById(id);
		ResponseStrcture<Product> structure = new ResponseStrcture<>();
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product found");
			structure.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new ProductNotFoundException("Cannot Update Product as Id is Invalid");
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findByMerchantId(int merchant_id) {
		ResponseStrcture<List<Product>> structure = new ResponseStrcture<>();
		List<Product> products = productDao.findByMerchantId(merchant_id);
		if (products.isEmpty())
			throw new ProductNotFoundException("No Product Found for entered Merchant Id");
		structure.setData(products);
		structure.setMessage("List of Products for entered Merchant Id");
		structure.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findByMerchantPhoneAndPassword(long phone, String password) {
		ResponseStrcture<List<Product>> structure = new ResponseStrcture<>();
		List<Product> products = productDao.findByMerchantPhoneAndPassword(phone, password);
		if (products.isEmpty())
			throw new ProductNotFoundException("No Product Found for entered Merchant");
		structure.setData(products);
		structure.setMessage("List of Products for entered phone number and password");
		structure.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findBetween(double min, double max) {
		ResponseStrcture<List<Product>> structure = new ResponseStrcture<>();
		List<Product> products = productDao.findBetween(min, max);
		if (products.isEmpty())
			throw new ProductNotFoundException("No Products Found in entered price range");
		structure.setData(products);
		structure.setMessage("List of Products between entered price range");
		structure.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	public ResponseEntity<ResponseStrcture<List<Product>>> findByName(String name) {
		ResponseStrcture<List<Product>> structure = new ResponseStrcture<>();
		structure.setData(productDao.findByName(name));
		structure.setMessage("List of Products between entered price range");
		structure.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}
}