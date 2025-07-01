package com.promineotech.mystore2.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.promineotech.mystore2.teacher.model.Teacher;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class AppUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
    
    private String address;
    
    private String password;
    
    private String role;
    
    private Date createdAt;
    
    private String passwordResetToken;
    
    
    //this safes profile picture to the data base
    @Lob
    private byte[] profilePicture;

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    @EqualsAndHashCode.Exclude //this avoids recursion.
	@ToString.Exclude
	@ManyToMany(mappedBy = "users")
	private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
}
