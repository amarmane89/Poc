package com.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.entity.ConsumerDetails;

@Repository
public interface ConsumerDetailsRepository extends JpaRepository<ConsumerDetails, Long> {
	
	List<ConsumerDetails>findByNameContainingIgnoreCase(String name);

}
