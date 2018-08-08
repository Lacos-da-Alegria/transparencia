package com.lacosdaalegria.transparencia.controller.controle;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lacosdaalegria.transparencia.model.controle.Categoria;
import com.lacosdaalegria.transparencia.service.controle.CategoriaService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CategoriaController {
	
private @NonNull CategoriaService categoriaService;
	
	@PostMapping("/admin/cadastrar/tipo")
	public String cadastrar(@Valid Categoria categoria, BindingResult result, RedirectAttributes redirectAttrs) {
		
		if(result.hasErrors()) {
			redirectAttrs.addFlashAttribute("errorMessage", "Erro! Categoria não cadastrada por conter erros nas informações");
		} else {
			redirectAttrs.addFlashAttribute("successMessage", "Categoria cadastrada com sucesso");
			categoriaService.save(categoria);
		}
		
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/categorias")
	public String list(@RequestParam(value = "pagina", required = false) Integer pagina, Model model) {
		
		model.addAttribute("page", categoriaService.page(pagina));
		
		return "controle/list/categorias";
	}

}
