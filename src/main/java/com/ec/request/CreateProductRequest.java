package com.ec.request;

import java.util.HashSet;
import java.util.Set;

import com.ec.model.Size;

import lombok.Data;

@Data
public class CreateProductRequest {
	private String title;
	private String description;
	private int price;
	private int discountedPrecent;
	private String brand;
	private String color;
	private Set<Size> size = new HashSet<>();
	private String imageUrl;
	private String topLevelCategory;
	private String secondLevelCategory;
	private String thirdLevelCategory;
}
