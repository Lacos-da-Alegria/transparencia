package com.lacosdaalegria.transparencia.service.controle;

import org.springframework.stereotype.Service;

import com.lacosdaalegria.transparencia.repository.controle.AuditoriaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditoriaService {

	private @NonNull AuditoriaRepository auditoriaRepository;
	
}
