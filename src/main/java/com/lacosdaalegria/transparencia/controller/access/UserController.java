package com.lacosdaalegria.transparencia.controller.access;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.lacosdaalegria.transparencia.model.access.User;
import com.lacosdaalegria.transparencia.service.access.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private @NonNull UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "access/login"; 
	}
	
	@GetMapping("/admin/cadastrar")
	public String cadastrar(Model model) {
		
		model.addAttribute("user", new User());
		
		return "access/register"; 
	}

	@PostMapping("/admin/cadastrar")
	public String cadastrar(@Valid User user, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("user", user);
			
			return "access/register"; 
			
		} else {
			
			userService.save(user);
			
			model.addAttribute("user", new User());
			model.addAttribute("successMessage", "O usuário foi cadastrado com sucesso!");
			
			return "access/register"; 
			
		}
		
	}
	
	/**
	 * Metodo utilizada para desenvolvimento
	 * @return
	 */
	@GetMapping("/add/user")
	public String addAdmin() {
		
		User user = new User();
		
		user.setLogin("admin");
		user.setEmail("contato@lacosdaalegria.com");
		user.setContato("999999999");
		user.setSenha("admin");
		
		userService.addAdmin(user);
		
		return "access/login"; 
	}
	
}
