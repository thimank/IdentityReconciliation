package com.bitespeed.IdentityReconciliation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bitespeed.IdentityReconciliation.entity.Identity;

@Repository
public interface IdentityRepository extends JpaRepository<Identity,Integer> {
	
	@Query(value="select * from identity where email = :email or phone_number = :contact order by created_at",nativeQuery =true)
	List<Identity> getIdentityByEmailOrContact(String email,Long contact);

}
