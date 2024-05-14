package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product Save(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findProductById(Integer id) {
		return productRepository.findById(id);
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public List<Product> findByMerchantId(int id) {
		return productRepository.findByMerchantId(id);
	}

	public List<Product> findByMerchantPhoneAndPassword(long phone, String password) {
		return productRepository.findByMerchantPhoneAndPassword(phone, password);
	}

	public List<Product> findBetween(double min, double max) {
		return productRepository.findBetween(min, max);
	}

	public List<Product> findByName(String name) {
		
		return productRepository.findByName(name);
	}
}