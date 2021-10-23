package com.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import com.poc.constants.TransactionActivity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionActivities {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	private Long transactionId;
	private Long transactionActivityId;
	private TransactionActivity transactionActivityName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
