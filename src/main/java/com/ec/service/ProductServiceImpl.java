package com.ec.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ec.exception.ProductException;
import com.ec.model.Category;
import com.ec.model.Product;
import com.ec.model.Size;
import com.ec.repository.CategoryRepository;
import com.ec.repository.ProductRepository;
import com.ec.request.CreateProductRequest;


@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public Product createProduct(CreateProductRequest req) {
		Category topLevel = categoryRepository.findByName(req.getTopLevelCategory());
		if(topLevel==null) {
			Category topLeverCategory = new Category();
			topLeverCategory.setName(req.getTopLevelCategory());
			topLeverCategory.setLevel(1);
			topLevel = categoryRepository.save(topLeverCategory);
		}
		
		Category secondLevel = categoryRepository.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
		if(secondLevel== null) {
			Category secondLeverCategory = new Category();
			secondLeverCategory.setName(req.getSecondLevelCategory());
			secondLeverCategory.setParentCategory(topLevel);
			secondLeverCategory.setLevel(2);
			secondLevel = categoryRepository.save(secondLeverCategory);
		}
		
		Category thirdLevel = categoryRepository.findByNameAndParent(req.getThirdLevelCategory(),topLevel.getName());
		if(thirdLevel== null) {
			Category thirdLeverCategory = new Category();
			thirdLeverCategory.setName(req.getThirdLevelCategory());
			thirdLeverCategory.setParentCategory(secondLevel);
			thirdLeverCategory.setLevel(3);
			thirdLevel = categoryRepository.save(thirdLeverCategory);
		}
		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		int discount = req.getPrice()*req.getDiscountedPrecent()/100;
		product.setDiscountedPrice(req.getPrice()-discount);
		product.setBrand(req.getBrand());
		product.setCategory(thirdLevel);
		product.setDiscountPercent(req.getDiscountedPrecent());
		product.setImageUrl(req.getImageUrl());
		product.setPrice(req.getPrice());
		Set<Size> size = req.getSize();
		int quantity=0;
		for(Size s:size)
			quantity+=s.getQuantity();
		product.setQuantity(quantity);
		product.setSizes(size);
		product.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct = productRepository.save(product);
		
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product = findProductById(productId);
		product.getSizes().clear();
		productRepository.delete(product);
		return "Product Deleted Successfully";
		
	}

	@Override
	public Product updateProduct(Long ProductId, Product req) throws ProductException {
		Product product = findProductById(ProductId);
		if(req.getQuantity()!=0) {
			product.setQuantity(req.getQuantity());
		}
		
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> opt = productRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product not found with id : "+id );
		
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		List<Product> products;
		if (category != null && !category.isEmpty()) {
		    products = productRepository.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		
		
		
		if(!colors.isEmpty()) {
			products = products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		
		if(stock!= null) {
			if(stock.equals("in_stock")) {
				products = products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
			}
			else if(stock.equals("out_of_stock")) {
				products = products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
			}
		}
	}		
		 else {
			    products = findAllProducts();
			}
		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());
		
		List<Product> pageContent = products.subList(startIndex, endIndex);
		Page<Product> filteredProducts = new PageImpl<>(pageContent,pageable,products.size());
		return filteredProducts;
	}

	@Override
	public List<Product> findAllProducts() {
		List<Product> products = productRepository.findAll();
		return products;
	}

}
