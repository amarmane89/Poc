package com.poc.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.poc.constants.TransactionActivity;
import com.poc.dto.TransactionListResponceDto;
import com.poc.entity.Transaction;
import com.poc.entity.TransactionActivities;

public interface TransactionService {
	
	Transaction createTransaction(Transaction txn);
	Transaction updateTransaction(Long id, TransactionActivity status);
	List<TransactionListResponceDto> getTransactionList(String consumerId, Pageable pageable);
	
	Transaction getTransactionDetail(Long id);
	List<TransactionActivities> getTransactionActivities(Long id);
}
