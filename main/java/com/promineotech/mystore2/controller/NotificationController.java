package com.promineotech.mystore2.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.model.Notification;
import com.promineotech.mystore2.service.AppUserService;
import com.promineotech.mystore2.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    
	@Autowired
	private AppUserService appUserService;
	
    @Autowired
    private NotificationService notificationService;
    
    @GetMapping
    public List<Notification> getNotificationsForUser(Principal principal) {
        AppUser user = appUserService.getLoggedInUser(principal);
        return notificationService.getNotificationsForUser(user);
    }
    
    @PostMapping
    public void createNotification(@RequestBody Notification notification) {
        notificationService.createNotification(notification);
    }
}

