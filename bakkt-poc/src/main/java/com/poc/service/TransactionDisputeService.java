package com.poc.service;


import com.poc.dto.TransactionDisputeRequestDto;
import com.poc.entity.TransactionDispute;


public interface TransactionDisputeService {
	
	String createDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto);
	String acceptDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto);
	String rejectDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto);
	TransactionDispute updateAcceptedDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto);
	
}
