package com.lacosdaalegria.transparencia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lacosdaalegria.transparencia.repository.S3Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class S3Service {
	
	private @NonNull S3Repository s3Repository;
	
	public List<String> save(MultipartFile[] files, String tag){
		
		List<String> arquivos = new ArrayList<>();
		
		for(MultipartFile file : files) {
			arquivos.add(s3Repository.carregaImagem(tag, file));
		}
		
		return arquivos;
		
	}
	
	public String save(MultipartFile file, String tag){
		
		return s3Repository.carregaImagem(tag, file);
		
	}
	
	public String save(MultipartFile file, String tag, Long id){
		
		return s3Repository.carregaImagem(tag, file);
		
	}

}
