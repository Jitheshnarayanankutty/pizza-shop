package com.pizza.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@Value("${spring.application.name}")
    String appName;
	
	@GetMapping("/login")
	public String showLoginPage(Model model) {
		model.addAttribute("appName", appName);
		return "login";
	}
}
