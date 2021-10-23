package com.poc.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	AppErrorCodes code;

	public AppException(AppErrorCodes errorCodes) {
		super(errorCodes.getMessage());
		this.code = errorCodes;
	}
	
	public AppException(AppErrorCodes code, String message) {
	    super(message);
	    this.code = code;
	  }

	public HttpStatus getHttpStatus() {
		return code.getHttpStatus();
	}
}
