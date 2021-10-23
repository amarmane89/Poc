package com.poc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionDispute{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String txnActivityId;
	private Long txnId;
	private Double disputeFees;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Comments.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "fk", referencedColumnName = "id")
	private List<Comments> comments;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

}
