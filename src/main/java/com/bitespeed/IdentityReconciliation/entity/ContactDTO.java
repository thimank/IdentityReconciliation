package com.bitespeed.IdentityReconciliation.entity;

import java.util.List;

import lombok.Data;

@Data
public class ContactDTO {
	
	private Integer primaryContatctId;
	private List<String> emails;
	private List<String> phoneNumbers;
	private List<Integer> secondaryContactIds;

}
