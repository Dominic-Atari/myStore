package com.promineotech.mystore2.service;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.repository.AppUserRepository;

public class ProfileService {
	
	@Autowired 
	AppUserRepository repo;

	// Implement the service method to save the profile picture
    
    public AppUser getLoggedInUser(Principal principal) {
        return repo.findByEmail(principal.getName());
    }
   // Implement the service method to save the profile picture

    public void uploadProfilePicture(MultipartFile file, Principal principal) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to upload profile picture: file is empty");
        }

        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Only images are allowed");
        }

        AppUser user = repo.findByEmail(principal.getName());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        try {
            user.setProfilePicture(file.getBytes());
            repo.save(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload profile picture", e);
        }
    
	}
}
