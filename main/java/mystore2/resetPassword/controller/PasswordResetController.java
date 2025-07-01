package mystore2.resetPassword.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.promineotech.mystore2.model.AppUser;
import com.promineotech.mystore2.repository.AppUserRepository;

import mystore2.resetPassword.service.EmailService;

@Controller
public class PasswordResetController {

    @Autowired
    private AppUserRepository repo;

    @Autowired
    private EmailService emailService;

   @Autowired
   private PasswordEncoder passwordEncoder;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam("email") String email, Model model) {
        AppUser user = repo.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setPasswordResetToken(token);
            repo.save(user);
            emailService.sendPasswordResetEmail(user.getEmail(), token);
            model.addAttribute("message", "Password reset email sent!");
        } else {
            model.addAttribute("message", "Email not found!");
        }
        return "forgot-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        if (!password.equals(confirmPassword)) {
            model.addAttribute("message", "Passwords do not match!");
            return "reset-password";
        }
        
        AppUser user = repo.findByPasswordResetToken(token);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(password));
            user.setPasswordResetToken(null);
            repo.save(user);
            model.addAttribute("message", "Password reset successfully!");
            return "login";
        } else {
            model.addAttribute("message", "Invalid token!");
            return "forgot-password";
        }
    }

}