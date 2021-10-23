package com.poc.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.poc.entity.Comments;

import lombok.Data;


@Data
public class TransactionDisputeRequestDto{
	
	private Double disputeFees;
	private List<Comments> comments;
	
}
