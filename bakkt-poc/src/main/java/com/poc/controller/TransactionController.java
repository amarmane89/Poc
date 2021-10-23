package com.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.constants.TransactionActivity;
import com.poc.dto.TransactionDisputeRequestDto;
import com.poc.dto.TransactionListResponceDto;
import com.poc.entity.Transaction;
import com.poc.entity.TransactionActivities;
import com.poc.entity.TransactionActivityMetadata;
import com.poc.entity.TransactionDispute;
import com.poc.repository.TransactionActivityMetadataRepository;
import com.poc.service.TransactionDisputeService;
import com.poc.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/consumer/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private TransactionActivityMetadataRepository transactionActivityMetadataRepository;
	
	@Autowired
	private TransactionDisputeService transactionDisputeService;
	
	@PostMapping("/create")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction ) {

		log.info("Adding transaction!");
		return ResponseEntity.ok().body(transactionService.createTransaction(transaction));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Transaction> updateTransaction(@RequestParam("id") Long txnId,@RequestParam("status") TransactionActivity status) {

		log.info("Updating transaction!");
		return ResponseEntity.ok().body(transactionService.updateTransaction(txnId,status));
	}
	
	@PostMapping("/txn/activity/metadata")
	public ResponseEntity<List<TransactionActivityMetadata>> addTxnActivityMetadata(@RequestBody List<TransactionActivityMetadata> list ) {

		return ResponseEntity.ok().body(transactionActivityMetadataRepository.saveAll(list));
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<TransactionListResponceDto>> getTransactionList(@RequestParam("id") String consumerId,
			 @PageableDefault Pageable pageable) {
		 
		Pageable pageReq = PageRequest.of(pageable.getPageNumber(),
		          pageable.getPageSize(), Direction.DESC, "createdAt");

		List<TransactionListResponceDto> txnList = transactionService.getTransactionList(consumerId,pageReq);
		log.info("Getting transaction list!");
		if(!txnList.isEmpty()) {
		return ResponseEntity.ok().body(txnList);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@GetMapping()
	public ResponseEntity<Transaction> getTransactionDetail(@RequestParam("id") Long txnId) {
		log.info("Getting transaction details!");
		return ResponseEntity.ok().body(transactionService.getTransactionDetail(txnId));
	}
	
	@GetMapping("/activities")
	public ResponseEntity<List<TransactionActivities>> getTransactionActivities(@RequestParam("id") Long txnId) {

		log.info("Getting transaction activities!");
		return ResponseEntity.ok().body(transactionService.getTransactionActivities(txnId));
	}
	
	@PostMapping("/dispute/create")
	public ResponseEntity<String> createDispute(@RequestParam("id") Long txnId, @RequestBody TransactionDisputeRequestDto transactionDisputeRequestDto) {

		log.info("Creating dispute!");
		return ResponseEntity.ok().body(transactionDisputeService.createDispute(txnId,transactionDisputeRequestDto));
	}
	
	@PostMapping("/dispute/accept")
	public ResponseEntity<String> acceptDispute(@RequestParam("id") Long txnId, @RequestBody TransactionDisputeRequestDto transactionDisputeRequestDto) {

		log.info("Accepting dispute!");
		return ResponseEntity.ok().body(transactionDisputeService.acceptDispute(txnId,transactionDisputeRequestDto));
	}
	
	@PostMapping("/dispute/update")
	public ResponseEntity<TransactionDispute> updateDispute(@RequestParam("id") Long txnId, @RequestBody TransactionDisputeRequestDto transactionDisputeRequestDto) {

		log.info("Accepting dispute!");
		return ResponseEntity.ok().body(transactionDisputeService.updateAcceptedDispute(txnId,transactionDisputeRequestDto));
	}
	
	@PostMapping("/dispute/reject")
	public ResponseEntity<String> rejectDispute(@RequestParam("id") Long txnId, @RequestBody TransactionDisputeRequestDto transactionDisputeRequestDto) {

		log.info("Rejecting dispute!");
		return ResponseEntity.ok().body(transactionDisputeService.rejectDispute(txnId,transactionDisputeRequestDto));
	}

}
