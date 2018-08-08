package com.lacosdaalegria.transparencia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lacosdaalegria.transparencia.service.HomeService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NavegationController {
	
	private @NonNull HomeService homeService;

	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("home", homeService.pageInit());
		
		return "home";
	}
	
}
