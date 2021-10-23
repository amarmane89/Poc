package com.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.poc.constants.TransactionActivity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionActivityMetadata {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private TransactionActivity activityName;
	private String description;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
