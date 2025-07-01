package com.promineotech.mystore2.service;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService{

	@Autowired
	private AppUserRepository repo;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = repo.findByEmail(email);
		
		if(appUser !=null) {
			var springUser = User.withUsername(appUser.getEmail())
					.password(appUser.getPassword())
					.roles(appUser.getRole())
					.build();
			
			return springUser;
		} else {
	        throw new UsernameNotFoundException("User not found with email: " + email);}
	}
		public void savePasswordResetToken(String email, String token) {
	        AppUser user = repo.findByEmail(email);
	        user.setPasswordResetToken(token);
	        repo.save(user);
	    }
	    
	    public void sendPasswordResetEmail(String email, String token) {
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(email);
	        mailMessage.setSubject("Password Reset Request");
	        mailMessage.setText("Click the following link to reset your password: http://localhost:8080/reset-password?token=" + token);
	        javaMailSender.send(mailMessage);
	    
	
	    
}

	    
	    public void resetPassword(String token, String password) {
	    	AppUser user = repo.findByPasswordResetToken(token);
	        if (user != null) {
	            user.setPassword(passwordEncoder.encode(password));
	            user.setPasswordResetToken(null);
	            repo.save(user);
	        }
	    }
	    public String getLoggedInUserFirstName(Principal principal) {
	        AppUser user = getLoggedInUser(principal);
	        return user.getFirstName();
	    
	    }
	    
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

