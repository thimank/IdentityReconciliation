package com.bitespeed.IdentityReconciliation.service;

import com.bitespeed.IdentityReconciliation.entity.IdentityRequest;
import com.bitespeed.IdentityReconciliation.entity.IdentityResponse;

public interface IdentityService {
	
	IdentityResponse IdentifyContact(IdentityRequest identityRequest) throws Exception;

}
