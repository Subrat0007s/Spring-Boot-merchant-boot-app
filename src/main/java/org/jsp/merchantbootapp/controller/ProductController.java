package org.jsp.merchantbootapp.controller;

import java.util.List;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStrcture;
import org.jsp.merchantbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/{m_id}")
	public ResponseEntity<ResponseStrcture<Product>> SaveProduct(@RequestBody Product product,
			@PathVariable(name = "m_id") Integer m_id) {
		return productService.saveProduct(product, m_id);
	}

	@PutMapping
	public ResponseEntity<ResponseStrcture<Product>> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@GetMapping
	public ResponseEntity<ResponseStrcture<Product>> findById(Integer id) {
		return productService.findProductById(id);
	}

	@GetMapping("/find-by-brand/{brand}")
	public ResponseEntity<ResponseStrcture<List<Product>>> findByBrand(@PathVariable(name = "brand") String brand) {
		return productService.findProductByBrand(brand);
	}

	@GetMapping("/find-by-category/{category}")
	public ResponseEntity<ResponseStrcture<List<Product>>> findByCategory(
			@PathVariable(name = "category") String category) {
		return productService.findProductByCategory(category);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStrcture<Product>> deleteById(Integer id){
		return null;
		
	}
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStrcture<List<Product>>> findByName(@PathVariable String name) {
		return productService.findByName(name);
	}

	@GetMapping("/{merchant_id}")
	public ResponseEntity<ResponseStrcture<List<Product>>> findByMerchantId(@PathVariable int merchant_id) {
		return productService.findByMerchantId(merchant_id);
	}

	@PostMapping("/find-by-merchant")
	public ResponseEntity<ResponseStrcture<List<Product>>> findByMerchantPhoneAndPassword(@RequestParam long phone,
			@RequestParam String password) {
		return productService.findByMerchantPhoneAndPassword(phone, password);
	}

	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<ResponseStrcture<Product>> findById(@PathVariable int id) {
		return productService.findById(id);
	}

	@GetMapping("/between/{min}/{max}")
	public ResponseEntity<ResponseStrcture<List<Product>>> findBetween(@PathVariable double min,
			@PathVariable double max) {
		return productService.findBetween(min, max);
	}
}