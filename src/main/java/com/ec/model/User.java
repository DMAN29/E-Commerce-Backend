package com.ec.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String role;
	
	private Long mobile;
	
	@OneToMany(mappedBy = "user",cascade =CascadeType.ALL )
	private List<Address> address= new ArrayList<>();
	
	@Embedded
	@ElementCollection
	@CollectionTable(name="payment info",joinColumns = @JoinColumn(name="u_id"))
	private List<PaymentInfo> paymentInfo = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade =CascadeType.ALL )
	@JsonIgnore
	private List<Rating> ratings = new ArrayList<>();
		
	@JsonIgnore
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Review> rivews = new ArrayList<>();
	
	
	private LocalDateTime createdAt;
}