package com.lacosdaalegria.transparencia.controller.controle;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.transparencia.dto.Filtro;
import com.lacosdaalegria.transparencia.service.controle.CategoriaService;
import com.lacosdaalegria.transparencia.service.controle.ItemService;
import com.lacosdaalegria.transparencia.service.financeiro.MovimentoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AnalistaController {
	
	private @NonNull ItemService itemService;
	private @NonNull CategoriaService categoriaService;
	private @NonNull MovimentoService movimentoService;
	
	@InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	} 
	
	@GetMapping("/admin")
	public String cadastro(Model model) {
		
		model.addAttribute("categorias", categoriaService.all());
		model.addAttribute("itens", itemService.all());
		
		return "controle/cadastrar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisa(Filtro filtro, Model model, 
			@RequestParam(value = "pagina", required = false) Integer pagina) {
		
		model.addAttribute("filtro", filtro);
		
		model.addAttribute("categorias", categoriaService.all());
		model.addAttribute("itens", itemService.all());
		
		model.addAttribute("page", movimentoService.page(pagina, filtro));
		
		return "pesquisa";
	}
	
}
