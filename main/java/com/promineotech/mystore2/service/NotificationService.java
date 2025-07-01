package com.promineotech.mystore2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.model.Notification;
import com.promineotech.mystore2.repository.NotificationRepository;

@Service
public class NotificationService {
    
	 @Autowired
	    private NotificationRepository notificationRepository;
	    
	    public List<Notification> getNotificationsForUser(AppUser user) {
	        return notificationRepository.findByUser(user);
	    }
	    
	    public void createNotification(Notification notification) {
	        notificationRepository.save(notification);
	    }
	    
	    // Other methods for updating and deleting notifications
	}
