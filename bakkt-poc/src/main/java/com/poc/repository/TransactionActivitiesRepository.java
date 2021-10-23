package com.poc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.poc.entity.TransactionActivities;

@Repository
public interface TransactionActivitiesRepository extends JpaRepository<TransactionActivities, String> {
	
	List<TransactionActivities> findByTransactionIdOrderByCreatedAtDesc(Long txnId);

}
