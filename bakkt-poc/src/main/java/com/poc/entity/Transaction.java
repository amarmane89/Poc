package com.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

import com.poc.constants.Currency;
import com.poc.constants.TransactionActivity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private TransactionActivity status;
	private String statusId;
	private String consumerId;
	private String description;
	private Double fees;
	private String receiverName;
	private String receiverAccountNumber;
	private Currency currency;
	private Double amount;
	private Double totalAmount;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
