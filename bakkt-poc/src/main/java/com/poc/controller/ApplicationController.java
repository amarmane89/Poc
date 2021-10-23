package com.poc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/application")
public class ApplicationController {
	
	@GetMapping("/status")
	public ResponseEntity<String> applicationStatus() {
		log.info("Application is runing..!");
		return ResponseEntity.ok().body("Welcome!!");
	}

}
