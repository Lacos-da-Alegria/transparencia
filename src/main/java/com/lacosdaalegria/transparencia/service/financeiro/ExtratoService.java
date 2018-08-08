package com.lacosdaalegria.transparencia.service.financeiro;

import org.springframework.stereotype.Service;

import com.lacosdaalegria.transparencia.repository.financeiro.ExtratoRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExtratoService {
	
	private @NonNull ExtratoRepository extratoRepository;

}
