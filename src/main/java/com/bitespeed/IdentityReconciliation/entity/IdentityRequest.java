package com.bitespeed.IdentityReconciliation.entity;

import lombok.Data;

@Data
public class IdentityRequest {
	
	private String email;
	private Long phoneNumber;
	
}
