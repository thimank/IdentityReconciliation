package com.bitespeed.IdentityReconciliation.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bitespeed.IdentityReconciliation.entity.ContactDTO;
import com.bitespeed.IdentityReconciliation.entity.Identity;
import com.bitespeed.IdentityReconciliation.entity.IdentityRequest;
import com.bitespeed.IdentityReconciliation.entity.IdentityResponse;
import com.bitespeed.IdentityReconciliation.repository.IdentityRepository;
import com.bitespeed.IdentityReconciliation.service.IdentityService;

import jakarta.transaction.Transactional;

@Service
public class IdentityServiceImpl implements IdentityService{
	
	@Autowired
	IdentityRepository identityRepository;

	@Transactional(rollbackOn = Exception.class)
	@Override
	public IdentityResponse IdentifyContact(IdentityRequest identityRequest) throws Exception{
		String email = identityRequest.getEmail();
		Long contact = identityRequest.getPhoneNumber();
		
		//searching the DB for matching email or phoneNumber
		List<Identity> identities = identityRepository.getIdentityByEmailOrContact(email,contact);
		
		Identity newIdentity = new Identity();
		List<String> contactList = new ArrayList<>();
		List<String> emailList = new ArrayList<>();
		List<Integer> secondaryContactList = new ArrayList<>();

		
		//Case 1 : when database already contains the identity of user
		if(identities != null && !identities.isEmpty()) {
			emailList.add(identities.get(0).getEmail());
			contactList.add(identities.get(0).getPhoneNumber().toString());
			
			for(Identity identity : identities) {
				Identity savedIdentity = null;
				newIdentity.setCreatedAt(new Timestamp(new Date().getTime()));
				newIdentity.setUpdatedAt(new Timestamp(new Date().getTime()));
				newIdentity.setEmail(email);
				newIdentity.setPhoneNumber(contact);
				newIdentity.setDeletedAt(null);
				
				if(Objects.equals(identity.getPhoneNumber(), contact) && !Objects.equals(identity.getEmail(), email)) {
					newIdentity.setLinkedId(null);
					newIdentity.setLinkPrecedence("secondary");
					
					//saving the new data to DB
					savedIdentity = identityRepository.save(newIdentity);				
					emailList.add(savedIdentity.getEmail());
				}
				if((!Objects.equals(identity.getPhoneNumber(), contact) && Objects.equals(identity.getEmail(), email))) {
					newIdentity.setLinkedId(identities.get(0).getId());
					newIdentity.setLinkPrecedence("secondary");
					
					//saving the new data to DB
					savedIdentity = identityRepository.save(newIdentity);	
					contactList.add(savedIdentity.getPhoneNumber().toString());
				}
				secondaryContactList.add(savedIdentity != null ? savedIdentity.getId() : null);
			}
			
		}
		// Case 2: When there is no match in the DB
		else {
			newIdentity.setCreatedAt(new Timestamp(new Date().getTime()));
			newIdentity.setUpdatedAt(new Timestamp(new Date().getTime()));
			newIdentity.setEmail(email);
			newIdentity.setPhoneNumber(contact);
			newIdentity.setDeletedAt(null);
			newIdentity.setLinkedId(null);
			newIdentity.setLinkPrecedence("primary");
			identityRepository.save(newIdentity);
			emailList.add(email);
			contactList.add(contact.toString());
		}
			
		//Creating Response
		IdentityResponse identityResponse = new IdentityResponse();
		ContactDTO contactDto = new ContactDTO();
		contactDto.setEmails(emailList);
		contactDto.setPhoneNumbers(contactList);
		contactDto.setSecondaryContactIds(secondaryContactList);
		contactDto.setPrimaryContatctId(!identities.isEmpty() ? identities.get(0).getId() : null);
		identityResponse.setContact(contactDto);
		
		return identityResponse;
	}

}
