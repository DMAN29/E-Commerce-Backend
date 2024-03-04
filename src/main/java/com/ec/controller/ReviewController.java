package com.ec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.exception.ProductException;
import com.ec.exception.UserException;
import com.ec.model.Review;
import com.ec.model.User;
import com.ec.request.ReviewRequest;
import com.ec.service.ReviewService;
import com.ec.service.UserService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	

	@Autowired
	private UserService userService;
	
	@Autowired 
	private ReviewService reviewService;
	
	@PostMapping("/create")
	public ResponseEntity<Review> createreview(@RequestBody ReviewRequest req,	@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		User user = userService.findUserProfileByJwt(jwt);
		Review review = reviewService.createReview(req, user);
		return new ResponseEntity<Review>(review,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Review>>getProductsreview(@PathVariable Long productId,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		
		User user = userService.findUserProfileByJwt(jwt);
		List<Review> reviews = reviewService.getAllReview(productId);
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.ACCEPTED);
	}

}
