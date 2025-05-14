package com.ec.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {

	private String cardholderName;
	
	private String cardNumber;
	
	private LocalDate expirationDate;
	
	private String cvv;
	
}
