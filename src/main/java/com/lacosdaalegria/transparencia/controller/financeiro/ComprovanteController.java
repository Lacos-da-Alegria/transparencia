package com.lacosdaalegria.transparencia.controller.financeiro;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lacosdaalegria.transparencia.model.financeiro.Comprovante;
import com.lacosdaalegria.transparencia.model.financeiro.Movimento;
import com.lacosdaalegria.transparencia.service.financeiro.ComprovanteService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ComprovanteController {

	private @NonNull ComprovanteService comprovanteService; 
	
	@GetMapping("/controle/comprovantes")
	public String list(Movimento movimento,
					   Model model) {
		
		model.addAttribute("movimento", movimento);
		
		return "controle/list/comprovantes";
	}
	
	@ResponseBody
	@GetMapping("/comprovantes")
	public List<Comprovante> comprovantes(Movimento movimento) {
		return movimento.getComprovantes();
	}
	
}
