package com.ec.service;

import java.util.List;

import com.ec.exception.OrderException;
import com.ec.model.Address;
import com.ec.model.Order;
import com.ec.model.User;

public interface OrderService {
		
	public Order createOrder(User user, Address shippingAddress);
	
	public Order findOrderById(Long orderIdLong) throws OrderException;
	
	public List<Order> userOrderHistory(Long usrId);
	
	public Order placedOrder(Long orderId)throws OrderException;
	
	public Order confirmedOrder(Long orderId)throws OrderException;
	
	public Order shippedOrder(Long orderId)throws OrderException;
	
	public Order deliveredOrder(Long orderId)throws OrderException;
	
	public Order cancledOrder(Long orderId)throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId)throws OrderException;
}
