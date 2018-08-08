package com.lacosdaalegria.transparencia.controller.controle;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacosdaalegria.transparencia.model.controle.Item;
import com.lacosdaalegria.transparencia.service.controle.ItemService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemControler {
	
	private @NonNull ItemService itemService;
	
	@PostMapping("/admin/cadastrar/item")
	public String cadastrar(@Valid Item item, BindingResult result, RedirectAttributes redirectAttrs) {
		
		if(result.hasErrors()) {
			redirectAttrs.addFlashAttribute("errorMessage", "Erro! Item não cadastrado por conter erros nas informações");
		} else {
			redirectAttrs.addFlashAttribute("successMessage", "Item cadastrado com sucesso");
			itemService.save(item);
		}
		
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/itens")
	public String list(@RequestParam(value = "pagina", required = false) Integer pagina, Model model) {
		
		model.addAttribute("page", itemService.page(pagina));
		
		return "controle/list/itens";
	}
		
}
