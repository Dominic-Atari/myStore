package com.promineotech.mystore2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.mystore2.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByEmail(String email);
//-------------------------------------------------------------------
	public AppUser findByPasswordResetToken(String token);

	
	
}
