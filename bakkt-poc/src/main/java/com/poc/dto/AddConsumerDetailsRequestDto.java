package com.poc.dto;

import lombok.Data;

@Data
public class AddConsumerDetailsRequestDto {
	
	private String name;
	private String middleName;
	private String lastName;
	private String mobileNumber;
	private String countryCode;
	private String emailId;
	private String createdAt;
	private String isEnabled;

}
