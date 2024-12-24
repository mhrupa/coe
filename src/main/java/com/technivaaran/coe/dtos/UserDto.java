package com.technivaaran.coe.dtos;

import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

	@NonNull
	private String userName;

	@NonNull
	// @Email
	private String email;

	// @Length(max = 50)
	@NonNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private String status;

	private LocalDateTime lastLoginOn;

	private boolean firstLogin;

	@NonNull
	private Integer branchId;

	@NonNull
	private Integer roleId;
}
