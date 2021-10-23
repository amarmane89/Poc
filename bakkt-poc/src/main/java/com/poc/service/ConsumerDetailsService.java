package com.poc.service;

import java.util.List;

import com.poc.dto.CosumerSearchResponceDto;
import com.poc.entity.ConsumerDetails;

public interface ConsumerDetailsService {
	
	List<ConsumerDetails> addConsumers(List<ConsumerDetails> list);
	List<CosumerSearchResponceDto> searchConsumer(String name);
	ConsumerDetails getConsumer(Long id);
}
