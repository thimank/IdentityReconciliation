package com.bitespeed.IdentityReconciliation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitespeed.IdentityReconciliation.entity.IdentityRequest;
import com.bitespeed.IdentityReconciliation.entity.IdentityResponse;
import com.bitespeed.IdentityReconciliation.service.IdentityService;

@RestController
public class IdentityController {
	
	@Autowired
	IdentityService identityService;
	
	@PostMapping("/identify")
	public IdentityResponse IdentifyContact(@RequestBody IdentityRequest identityRequest)  throws Exception{
		return identityService.IdentifyContact(identityRequest);
	}

}
