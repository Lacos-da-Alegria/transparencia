package com.lacosdaalegria.transparencia.controller.financeiro;

import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.DESPESA;
import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.RECEITA;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lacosdaalegria.transparencia.dto.MovimentoDTO;
import com.lacosdaalegria.transparencia.model.financeiro.Movimento;
import com.lacosdaalegria.transparencia.service.controle.CargaService;
import com.lacosdaalegria.transparencia.service.financeiro.MovimentoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MovimentoController {
	
	private @NonNull MovimentoService movimentoService;
	private @NonNull CargaService cargaService;
	
	@GetMapping("/controle/despesas")
	public String despesas(@RequestParam(value = "pagina", required = false) Integer pagina, Model model) {
		
		model.addAttribute("pagina", "Despesas");
		model.addAttribute("page", movimentoService.page(pagina, DESPESA));
		
		return "controle/list/movimentos";
	}
	
	@GetMapping("/controle/receitas")
	public String receitas(@RequestParam(value = "pagina", required = false) Integer pagina, Model model) {
		
		model.addAttribute("pagina", "Receitas");
		model.addAttribute("page", movimentoService.page(pagina, RECEITA));
		
		return "controle/list/movimentos";
	}
	
	@GetMapping("/admin/carga/movimentacao")
	public String carga() {
		return "controle/carga";
	}
	
	@PostMapping("/admin/carga/movimentacao")
	public String carga(String mes, String ano, MultipartFile carga, Model model) {
		
		if(!cargaService.valida(mes, ano)) {
			model.addAttribute("successMessage", "Carga realizada com sucesso");
			cargaService.carga(carga, mes, ano);
		} else {
			model.addAttribute("errorMessage", "Erro! Carga não realizada por já existir uma carga para esse mês");
		}
		
		return "controle/carga";
	}
	
	@PostMapping("/controle/atualiza/movimento")
	public String atualizaMovimento(MovimentoDTO movimentoDTO) {
		movimentoService.update(movimentoDTO);
		return "redirect:" + movimentoDTO.getUrl();
	}
	
	@ResponseBody
	@GetMapping("/controle/get")
	public Movimento movimento(Movimento movimento) {
		return movimento;
	}
	
	/**
	 
	 Método deve ser descomentado caso queira se cadastrar movimento manualmente
	 
	 Tambem deve se alterar a pagina de controle para isso
	
	 @InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	} 
	
	@PostMapping("/controle/cadastrar/movimento")
	public String cadastrar(Movimento movimento, MultipartFile[] comprovantes, 
			RedirectAttributes redirectAttrs) {
		
		if(despesaService.valid(movimento)) {
			redirectAttrs.addFlashAttribute("successMessage", "Despesa cadastrada com sucesso");
			despesaService.save(movimento, comprovantes);
		} else {
			redirectAttrs.addFlashAttribute("errorMessage", "Erro! Despesa não cadastrada por conter erros nas informações");
		}
		
		return "redirect:/controle";
	}
	 */
	
}
