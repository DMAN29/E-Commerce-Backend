package com.ec.service;

import com.ec.exception.CartItemException;
import com.ec.exception.ProductException;
import com.ec.exception.UserException;
import com.ec.model.Cart;
import com.ec.model.User;
import com.ec.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req)throws ProductException, CartItemException, UserException;
	
	public Cart findUserCart(Long userId);
	
}
