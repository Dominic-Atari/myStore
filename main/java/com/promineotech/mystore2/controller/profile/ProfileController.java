package com.promineotech.mystore2.controller.profile;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.service.AppUserService;

@Controller
public class ProfileController {
	
	 @Autowired
	    private AppUserService appUserService;
	
	//Create a controller method to handle the file upload

    @PostMapping("/profile/upload-picture")
    public String uploadProfilePicture(@RequestParam("file") MultipartFile file, Principal principal) {
        appUserService.uploadProfilePicture(file, principal);
        return "redirect:/profile";
    }
    
    //Create a controller method to serve the profile picture

    @GetMapping("/profile/picture")
    public ResponseEntity<byte[]> getProfilePicture(Principal principal) {
        AppUser user = appUserService.getLoggedInUser(principal);
        if (user.getProfilePicture() != null) {
            byte[] picture = user.getProfilePicture();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
        } else {
            // Return a default image or handle the case when the user doesn't have a profile picture
            return ResponseEntity.notFound().build();
        }
    }
}

