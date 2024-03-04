package com.ec.service;

import java.util.List;

import com.ec.exception.ProductException;
import com.ec.model.Rating;
import com.ec.model.User;
import com.ec.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user)throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);
}
