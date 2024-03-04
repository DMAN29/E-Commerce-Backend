package com.ec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ec.exception.ProductException;
import com.ec.model.Product;
import com.ec.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long ProductId,Product req)throws ProductException;
	
	public Product findProductById(Long id)throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public List<Product> findAllProducts();
	
	public Page<Product> getAllProduct(String category,List<String> colors, List<String>sizes,Integer minPrice,Integer maxPrice,Integer minDiscount, String sort,String stock,Integer pageNumber,Integer pageSize);
	
	
}
