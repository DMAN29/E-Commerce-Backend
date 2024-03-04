package com.ec.service;

import com.ec.exception.CartItemException;
import com.ec.exception.UserException;
import com.ec.model.Cart;
import com.ec.model.CartItem;
import com.ec.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(long userId, Long id, CartItem cartItem)throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size,Long userId );
	
	public void removeCardItem(Long userId, Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	
	
}
