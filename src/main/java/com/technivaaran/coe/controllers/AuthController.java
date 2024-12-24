package com.technivaaran.coe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.technivaaran.coe.dtos.AppUserDto;
import com.technivaaran.coe.dtos.UserResponseDto;
import com.technivaaran.coe.exception.UserNotFoundException;
import com.technivaaran.coe.services.UserService;
import com.technivaaran.coe.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(AppConstants.BASE_URL)
@Slf4j
public class AuthController {

	private UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@SuppressWarnings("null")
	@PostMapping(value = "/validate")
	public String validateUser(@RequestBody AppUserDto appUserDto, Model model) {
		try {
			UserResponseDto userResponseDto = userService.getUserByEmailAndPassword(appUserDto.getUsername(),
					appUserDto.getPassword());
			model.addAttribute("user", userResponseDto);

			return "dashboardLayout/dashboard";
		} catch (UserNotFoundException e) {
			RequestContextHolder.getRequestAttributes().setAttribute("errorMsg", e.getMessage(),
					RequestAttributes.SCOPE_REQUEST);
			return "forward:/api/error";
		}
	}
}
