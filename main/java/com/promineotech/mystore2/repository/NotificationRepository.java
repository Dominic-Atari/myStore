package com.promineotech.mystore2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByUser(AppUser user);
	
	

}
