package com.ec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ec.exception.CartItemException;
import com.ec.exception.ProductException;
import com.ec.exception.UserException;
import com.ec.model.Cart;
import com.ec.model.CartItem;
import com.ec.model.Product;
import com.ec.model.User;
import com.ec.repository.CartRepository;
import com.ec.request.AddItemRequest;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Cart createCart(User user) {
		
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
		
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException, CartItemException, UserException {
	    Cart cart = cartRepository.findByUserId(userId);
	    Product product = null;
	    try {
	        product = productService.findProductById(req.getProductId());
	    } catch (ProductException e) {
	        throw new ProductException("Product not found");
	    }
	    
	    CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
	    
	    try {
	        if(isPresent == null) {
	            CartItem cartItem  = new CartItem();
	            cartItem.setProduct(product);
	            cartItem.setCart(cart);
	            cartItem.setQuantity(req.getQuantity());
	            cartItem.setUserId(userId);
	            cartItem.setPrice(req.getQuantity()*product.getPrice());
	            cartItem.setDiscountedPrice(req.getQuantity()*product.getDiscountedPrice());
	            cartItem.setSize(req.getSize());
	            cart.getCartItems().add(cartItem);
	            cartItem.setCart(cart);
	            cartItemService.createCartItem(cartItem);
	        }
	        else {
	            isPresent.setQuantity(isPresent.getQuantity()+1);
	            isPresent.setPrice(req.getQuantity()*product.getPrice());
	            isPresent.setDiscountedPrice(req.getQuantity()*product.getDiscountedPrice());
	            cartItemService.updateCartItem(userId, isPresent.getId(), isPresent);
	        }
	        return "Item Add to Cart";
	    } catch (CartItemException e) {
	        throw new CartItemException("Error creating or updating cart item");
	    }
	}
	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartItem : cart.getCartItems()) {
			totalPrice+= cartItem.getPrice();
			totalDiscountedPrice += cartItem.getDiscountedPrice();
			totalItem += cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		
		return cartRepository.save(cart);
	}

}