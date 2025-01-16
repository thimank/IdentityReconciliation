package com.bitespeed.IdentityReconciliation.entity;

import java.sql.Timestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Identity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;                                     
	private Long phoneNumber;        
	private String email;               
	private Integer linkedId;            
	private String linkPrecedence;      
	private Timestamp createdAt;                        
	private Timestamp updatedAt;                       
	private Timestamp deletedAt;  
	
}
