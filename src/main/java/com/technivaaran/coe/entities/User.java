package com.technivaaran.coe.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<Long> {

	@Column(name = "user_name", nullable = false, unique = true, length = 50)
	private String userName;

	@Column(name = "email", nullable = false, length = 50)
	// @Email
	private String email;

	@Column(name = "password", nullable = false, length = 50)
	@JsonIgnore
	private String password;

	@Column(name = "status", nullable = false, columnDefinition = "varchar(10) default 'INACTIVE'")
	private String status;

	@Column(name = "last_login_on", nullable = false)
	@UpdateTimestamp
	@JsonIgnore
	private LocalDateTime lastLoginOn;

	@Column(name = "first_login", nullable = false, columnDefinition = "tinyint(1) default 1")
	@JsonIgnore
	private boolean firstLogin;

}