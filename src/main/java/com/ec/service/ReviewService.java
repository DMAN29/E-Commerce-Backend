package com.ec.service;

import java.util.List;

import com.ec.exception.ProductException;
import com.ec.model.Review;
import com.ec.model.User;
import com.ec.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
}
