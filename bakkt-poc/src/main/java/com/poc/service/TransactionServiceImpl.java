package com.poc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poc.constants.TransactionActivity;
import com.poc.dto.TransactionListResponceDto;
import com.poc.entity.Transaction;
import com.poc.entity.TransactionActivities;
import com.poc.entity.TransactionActivityMetadata;
import com.poc.exception.handler.AppErrorCodes;
import com.poc.exception.handler.AppException;
import com.poc.repository.TransactionActivitiesRepository;
import com.poc.repository.TransactionActivityMetadataRepository;
import com.poc.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionActivitiesRepository transactionActivitiesRepository;
	
	@Autowired
	private TransactionActivityMetadataRepository transactionActivityMetadataRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public Transaction createTransaction(Transaction txn) {
		txn.setCreatedAt(LocalDateTime.now());
		txn.setUpdatedAt(LocalDateTime.now());
		Transaction savedTransaction = transactionRepository.save(txn);
		return updateTransaction(savedTransaction.getId(),savedTransaction.getStatus());
	}

	@Override
	public List<TransactionListResponceDto> getTransactionList(String consumerId, Pageable pageable) {
	return transactionRepository.findByConsumerId(consumerId,pageable).getContent().stream()
	 .map(f -> mapper.map(f, TransactionListResponceDto.class)).collect(Collectors.toList());
		 
	}

	@Override
	public Transaction updateTransaction(Long id, TransactionActivity status) {
		Optional<Transaction> existingTransaction = transactionRepository.findById(id);
		if(existingTransaction.isPresent()) {
			Transaction txn = existingTransaction.get();
			TransactionActivities txnActivity = createTxnActivity(txn,status);
			txn.setStatus(status);
			txn.setStatusId(txnActivity.getId());
			txn.setUpdatedAt(LocalDateTime.now());
			return transactionRepository.save(txn);
		}else {
			throw new AppException(AppErrorCodes.RECORD_NOT_FOUND);
		}
	}
	

	private TransactionActivities createTxnActivity(Transaction transaction, TransactionActivity status) {
		TransactionActivities txnActivity = new TransactionActivities();
		TransactionActivityMetadata activityMedatadat = transactionActivityMetadataRepository.findByActivityName(status);
		txnActivity.setTransactionActivityId(activityMedatadat.getId());
		txnActivity.setTransactionActivityName(activityMedatadat.getActivityName());
		txnActivity.setTransactionId(transaction.getId());
		txnActivity.setCreatedAt(LocalDateTime.now());
		txnActivity.setUpdatedAt(LocalDateTime.now());
		return transactionActivitiesRepository.save(txnActivity);
	}

	@Override
	public Transaction getTransactionDetail(Long id) {
		try {
			return transactionRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new AppException(AppErrorCodes.RECORD_NOT_FOUND);
		}
	}

	@Override
	public List<TransactionActivities> getTransactionActivities(Long id) {
		
		return transactionActivitiesRepository.findByTransactionIdOrderByCreatedAtDesc(id);
	}
}
