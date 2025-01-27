package com.technivaaran.coe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.technivaaran.coe.utils.AppConstants;

@Controller
@RequestMapping(AppConstants.BASE_URL)
public class NavigationController {

	@GetMapping(value = "/")
	public String getIndexPage() {
		return "index";
	}

	@GetMapping(value = "/login")
	public String getLoginPage() {
		return "login";
	}

	@GetMapping(value = "/desktop")
	public String getDesktopPage() {
		return "desktop";
	}

	@GetMapping(value = "/sidebar")
	public String getSidebarPage() {
		return "dashboardLayout/sidebar";
	}

	@GetMapping(value = "/dashboard")
	public String getDashboard() {
		return "candidate/profile";
	}

	@GetMapping(value = "/candidate-profile")
	public String getCandidateProfile() {
		return "candidate/createProfile";
	}

	@GetMapping(value = "/candidate-screening")
	public String getCandidateScreening() {
		return "candidate/candidateScreening";
	}
}
