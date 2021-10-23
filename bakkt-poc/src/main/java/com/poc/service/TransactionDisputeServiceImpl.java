package com.poc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poc.constants.TransactionActivity;
import com.poc.dto.TransactionDisputeRequestDto;
import com.poc.entity.Transaction;
import com.poc.entity.TransactionDispute;
import com.poc.exception.handler.AppErrorCodes;
import com.poc.exception.handler.AppException;
import com.poc.repository.TransactionDisputeRepository;
import com.poc.repository.TransactionRepository;

@Service
public class TransactionDisputeServiceImpl implements TransactionDisputeService{

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionDisputeRepository transactionDisputeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Value("#{'${transaction.terminal.status}'.split(',')}")
	private List<TransactionActivity> terminalStatus;
	
	@Override
	public String createDispute(Long txnId,TransactionDisputeRequestDto transactionDisputeRequestDto) {
		
		Optional<Transaction> existingTransaction = transactionRepository.findById(txnId);
		if(existingTransaction.isPresent()) {
			Transaction txn = existingTransaction.get();
			if(terminalStatus.contains(txn.getStatus())) {
			Transaction updatedTransaction = transactionService.updateTransaction(txnId, TransactionActivity.DISPUTE_CREATED);
			TransactionDispute transactionDispute = mapper.map(transactionDisputeRequestDto, TransactionDispute.class);
			transactionDispute.setTxnId(txnId);
			transactionDispute.setTxnActivityId(updatedTransaction.getStatusId());
			transactionDispute.setCreatedAt(LocalDateTime.now());
			transactionDispute.setUpdatedAt(LocalDateTime.now());
			transactionDisputeRepository.save(transactionDispute);
			return "Dispute created for transactionId:"+txnId;
			}else {
				throw new AppException(AppErrorCodes.DISPUTE_CREATION_FAILED);
			}
		}else {
			throw new AppException(AppErrorCodes.RECORD_NOT_FOUND);
		}

	}

	@Override
	public String acceptDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto) {
		
		updateDispute(txnId,transactionDisputeRequestDto,TransactionActivity.DISPUTE_ACCEPTED);
		return "Dispute accepted for transactionId:"+txnId;
	}

	@Override
	public String rejectDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto) {
		updateDispute(txnId,transactionDisputeRequestDto,TransactionActivity.DISPUTE_REJECTED);
		return "Dispute rejected for transactionId:"+txnId;
	}
	
	private void updateDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto, TransactionActivity disputeAcceptedRejected) {
		Optional<Transaction> existingTransaction = transactionRepository.findById(txnId);
		if(existingTransaction.isPresent()) {
			Transaction txn = existingTransaction.get();
			TransactionDispute existingTxnDispute = transactionDisputeRepository.findByTxnActivityIdAndTxnId(txn.getStatusId(),txnId);
			Transaction updatedTransaction = transactionService.updateTransaction(txnId, disputeAcceptedRejected);	
			existingTxnDispute.getComments().addAll(transactionDisputeRequestDto.getComments());
			existingTxnDispute.setTxnActivityId(updatedTransaction.getStatusId());
			existingTxnDispute.setUpdatedAt(LocalDateTime.now());
			transactionDisputeRepository.save(existingTxnDispute);
			
		}else {
			throw new AppException(AppErrorCodes.RECORD_NOT_FOUND);
		}
		
	}
	
	@Override
	public TransactionDispute updateAcceptedDispute(Long txnId, TransactionDisputeRequestDto transactionDisputeRequestDto) {
		Optional<Transaction> existingTransaction = transactionRepository.findById(txnId);
		if(existingTransaction.isPresent()) {
			Transaction txn = existingTransaction.get();
			TransactionDispute existingTxnDispute = transactionDisputeRepository.findByTxnActivityIdAndTxnId(txn.getStatusId(),txnId);
			existingTxnDispute.getComments().addAll(transactionDisputeRequestDto.getComments());
			existingTxnDispute.setUpdatedAt(LocalDateTime.now());
			return transactionDisputeRepository.save(existingTxnDispute);
			
		}else {
			throw new AppException(AppErrorCodes.RECORD_NOT_FOUND);
		}
		
	}
}
