package com.lacosdaalegria.transparencia.service;

import org.springframework.stereotype.Service;

import com.lacosdaalegria.transparencia.dto.HomeDTO;
import com.lacosdaalegria.transparencia.service.financeiro.MovimentoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {
	
	private @NonNull MovimentoService movimentoService;
	
	public HomeDTO pageInit() {
		return movimentoService.paginaInicial();
	}

}
