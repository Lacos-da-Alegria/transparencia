package com.lacosdaalegria.transparencia.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lacosdaalegria.transparencia.model.controle.Categoria;
import com.lacosdaalegria.transparencia.model.controle.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovimentoDTO {

	private Long id;
	private Categoria categoria;
	private List<Item> itens;
	private String descricao;
	private MultipartFile[] comprovantes;
	private String url;
	
	public boolean temComprovantes() {
		if(comprovantes != null) {
			if(comprovantes.length > 1) {
				return true;
			} else {
				return !comprovantes[0].isEmpty();
			}
		} else 
			return false;
	}
	
	public boolean novaDescricao() {
		return descricao != null && !descricao.isEmpty();
	}
	
}
