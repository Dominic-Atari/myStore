package com.promineotech.mystore2.teacher.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.promineotech.mystore2.model.AppUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Teachrs")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name = "first_name")
	private String FirstName;
	
	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty
	@Email
	@Column(unique = true, nullable = false)
	private String email;
	private String phone;
	private String address;
	private Date createdAt;
	private String password;
	private String confirmPassword;
	private String passwordResetToken;
	
	@ManyToMany
	@JoinTable(
	    name = "app_user_teacher",
	    joinColumns = @JoinColumn(name = "teacher_id"),
	    inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private Set<AppUser> users = new HashSet<>();
}
