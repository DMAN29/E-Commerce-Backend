package com.ec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.exception.CartItemException;
import com.ec.exception.ProductException;
import com.ec.exception.UserException;
import com.ec.model.Cart;
import com.ec.model.CartItem;
import com.ec.model.User;
import com.ec.request.AddItemRequest;
import com.ec.response.ApiResponse;
import com.ec.service.CartItemService;
import com.ec.service.CartService;
import com.ec.service.UserService;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId,@RequestHeader("Authorization")String jwt)throws CartItemException,UserException{
		User user = userService.findUserProfileByJwt(jwt);
		cartItemService.removeCardItem(user.getId(), cartItemId);
		
		ApiResponse res = new ApiResponse();
		res.setMsg("item deleted from cart");
		res.setStatus(true);
		return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
	}
	
	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem,@PathVariable Long cartItemId,@RequestHeader("Authorization")String jwt)throws UserException,CartItemException{
		User user = userService.findUserProfileByJwt(jwt);
		CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		
		return new ResponseEntity<CartItem>(updateCartItem,HttpStatus.OK);
	}
	
}
