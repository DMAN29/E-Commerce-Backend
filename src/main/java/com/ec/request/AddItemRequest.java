package com.ec.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddItemRequest {

	private Long productId;
	private String size;
	private int quantity;
	private Integer price;
	
}
