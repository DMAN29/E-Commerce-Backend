package com.ec.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long id;
	  
	  private String title;
	  
	  private String description;
	  
	  private int price;
	  
	  private int discountedPrice;
	  
	  private int discountPercent;
	  
	  private int quantity;
	  
	  private String brand;
	  
	  private String color;
	  
	  @Embedded
	  @ElementCollection
	  private Set<Size> sizes = new HashSet<>();
	  
	  @Column(name="image url")
	  private String imageUrl;
	  
	  @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	  private List<Rating> ratings = new ArrayList<>();
	  
	  @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	  private List<Review> reviews = new ArrayList<>();
	  
	  @Column(name="num ratings")
	  private int numRatings;
	  
	  @ManyToOne
	  @JoinColumn(name="category_id")
	  private Category category;
	  
	  private LocalDateTime createdAt;
}
