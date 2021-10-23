package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poc.entity.TransactionDispute;

@Repository
public interface TransactionDisputeRepository extends JpaRepository<TransactionDispute, Long> {
	
	
	TransactionDispute findByTxnActivityIdAndTxnId(String txnActivityId, Long txnId);

}
