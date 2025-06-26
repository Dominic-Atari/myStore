package com.promineotech.mystore2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}

