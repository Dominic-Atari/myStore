package mystore2.resetPassword.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendPasswordResetEmail(String to, String token) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject("Password Reset Request");
		message.setText(
				"To reset your password, please click on the following link: http://localhost:8080/reset-password?token="
						+ token);
		javaMailSender.send(message);
	}
}
