package com.poc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.dto.CosumerSearchResponceDto;
import com.poc.entity.ConsumerDetails;
import com.poc.exception.handler.AppErrorCodes;
import com.poc.exception.handler.AppException;
import com.poc.repository.ConsumerDetailsRepository;

@Service
public class ConsumerDetailsServiceImpl implements ConsumerDetailsService{

	@Autowired
	private ConsumerDetailsRepository consumerDetailsRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<ConsumerDetails> addConsumers(List<ConsumerDetails> list) {
		list.stream().forEach(l -> l.setCreatedAt(LocalDateTime.now()));
		return consumerDetailsRepository.saveAll(list);
	}

	@Override
	public List<CosumerSearchResponceDto> searchConsumer(String name) {
		
		return consumerDetailsRepository.findByNameContainingIgnoreCase(name).stream()
				.map(f -> mapper.map(f, CosumerSearchResponceDto.class)).collect(Collectors.toList());
	}

	@Override
	public ConsumerDetails getConsumer(Long id) {
		try {
		return consumerDetailsRepository.findById(id).get();
		}catch (NoSuchElementException e) {
		throw new AppException(AppErrorCodes.RECORD_NOT_FOUND);
		}
		
	}

}
