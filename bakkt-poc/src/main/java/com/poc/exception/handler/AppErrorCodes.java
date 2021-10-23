package com.poc.exception.handler;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppErrorCodes {
	RECORD_NOT_FOUND("The record does not exist.", HttpStatus.NOT_FOUND),
	DISPUTE_CREATION_FAILED("Can not create dispute for in-progress transaction.", HttpStatus.BAD_REQUEST);
	private String message;
	private HttpStatus httpStatus;
}
