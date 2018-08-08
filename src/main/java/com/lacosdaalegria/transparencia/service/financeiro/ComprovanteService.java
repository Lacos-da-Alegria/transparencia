package com.lacosdaalegria.transparencia.service.financeiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.lacosdaalegria.transparencia.model.financeiro.Comprovante;
import com.lacosdaalegria.transparencia.model.financeiro.Movimento;
import com.lacosdaalegria.transparencia.repository.financeiro.ComprovanteRepository;
import com.lacosdaalegria.transparencia.service.S3Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComprovanteService {
	
	private @NonNull ComprovanteRepository comprovanteRepository;
	private @NonNull S3Service s3Service;
	
	@Transactional
	public Comprovante save(MultipartFile file, Movimento movimento) {
		
		Comprovante comprovante = factory(s3Service.save(file,  movimento.isReceita() ? "REC" : "DES"));
		
		comprovante.setMovimento(movimento);
		
		return comprovanteRepository.save(comprovante);
		
	}
	
	@Transactional
	public List<Comprovante> save(MultipartFile[] files, Movimento movimento) {
		
		List<Comprovante> comprovantes = factory(s3Service.save(files,  movimento.isReceita() ? "REC" : "DES"));
		
		comprovantes.forEach(c -> c.setMovimento(movimento));
		
		return  Lists.newArrayList(comprovanteRepository.saveAll(comprovantes));
		
	}
	
	private Comprovante factory(String imagem) {
		Comprovante comprovante = new Comprovante();
		comprovante.setImagem(imagem);
		return comprovante; 
	}
	
	private List<Comprovante> factory(List<String> imagens) {
		List<Comprovante> comprovantes = new ArrayList<>();
		for(String imagem : imagens) {
			comprovantes.add(factory(imagem));
		}
		return comprovantes;
	}


}
