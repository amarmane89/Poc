package com.poc.dto;

import java.time.LocalDateTime;

import com.poc.constants.TransactionActivity;

import lombok.Data;

@Data
public class TransactionListResponceDto {
	
	private Long id;
	private String consumerId;
	private TransactionActivity status;
	private String receiverName;
	private Double totalAmount;
	private LocalDateTime updatedAt;

}
