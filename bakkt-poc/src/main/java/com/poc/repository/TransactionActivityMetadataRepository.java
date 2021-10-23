package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.constants.TransactionActivity;
import com.poc.entity.TransactionActivityMetadata;

@Repository
public interface TransactionActivityMetadataRepository extends JpaRepository<TransactionActivityMetadata, Long> {
	
	TransactionActivityMetadata findByActivityName(TransactionActivity activityName);
	

}
