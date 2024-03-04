package com.ec.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart",cascade= CascadeType.ALL,orphanRemoval = true)
	private List<CartItem> cartItems= new ArrayList<CartItem>();
	
	private int totalPrice;
	
	private int totalItem;
	
	private int totalDiscountedPrice;
	
	private int discount;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", items=" + cartItems.size() + // only display the size of the items collection
                '}';
    }
	
 }
