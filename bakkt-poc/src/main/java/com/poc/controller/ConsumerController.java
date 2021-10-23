package com.poc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poc.dto.CosumerSearchResponceDto;
import com.poc.entity.ConsumerDetails;
import com.poc.service.ConsumerDetailsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class ConsumerController {
	
	@Autowired
	private ConsumerDetailsService consumerDetailsService;
	
	@PostMapping("/add")
	public ResponseEntity<List<ConsumerDetails>> addConsumer(@RequestBody List<ConsumerDetails> consumerDetailsList ) {
		
		log.info("Adding consumers!");
		return ResponseEntity.ok().body(consumerDetailsService.addConsumers(consumerDetailsList));
	}

	@GetMapping("/search")
	public ResponseEntity<List<CosumerSearchResponceDto>> searchConsumer(@RequestParam("name") String name) {
		
		log.info("Searching consumers!");
		return ResponseEntity.ok().body(consumerDetailsService.searchConsumer(name));
	}
	
	@GetMapping()
	public ResponseEntity<ConsumerDetails> getConsumerDetails(@RequestParam("id") Long id) {
		
		log.info("Getting consumers!");
		return ResponseEntity.ok().body(consumerDetailsService.getConsumer(id));
	}
}
