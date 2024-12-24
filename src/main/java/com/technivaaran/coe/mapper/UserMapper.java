package com.technivaaran.coe.mapper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.technivaaran.coe.dtos.UserDto;
import com.technivaaran.coe.dtos.UserResponseDto;
import com.technivaaran.coe.entities.User;
import com.technivaaran.coe.exception.EntityConversionException;
import com.technivaaran.coe.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper {

	public User convertToEntity(UserDto userDto) {
		try {

			return User.builder().userName(userDto.getUserName()).password(userDto.getPassword())
					.status(StringUtils.hasLength(userDto.getStatus()) ? userDto.getStatus()
							: AppConstants.STATUS_DISABLED)
					.email(userDto.getEmail()).build();
		} catch (Exception exception) {
			log.info("Error occurred while converting to User entity");
			throw new EntityConversionException(exception.getMessage(), exception);
		}
	}

	public UserResponseDto convertToUserResponseDto(User user) {

		return UserResponseDto.builder()
				.userName(user.getUserName())
				.email(user.getEmail())
				.status(user.getStatus())
				.lastLoginOn(user.getLastLoginOn())
				.firstLogin(user.isFirstLogin())
				.build();
	}
}
