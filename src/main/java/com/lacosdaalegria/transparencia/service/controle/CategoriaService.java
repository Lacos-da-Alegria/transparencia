package com.lacosdaalegria.transparencia.service.controle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lacosdaalegria.transparencia.model.controle.Categoria;
import com.lacosdaalegria.transparencia.repository.controle.CategoriaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

	private @NonNull CategoriaRepository categoriaRepository;
	
	public Categoria save(Categoria tipo) {
		return categoriaRepository.save(tipo);
	}
	
	public Iterable<Categoria> all(){
		return categoriaRepository.findAll();
	}
	
	public Categoria findByTag(String tag) {
		
		Categoria categoria = categoriaRepository.findByTag(tag);
		
		if(categoria == null) {
			categoria = categoriaRepository.findByTag("pendente");
		}
		
		return categoria;
			
	}
	
	public Page<Categoria> page(Integer page){
		
		if(page == null)
			page = 0;
		
		Page<Categoria> pagina = categoriaRepository.findAll(PageRequest.of(page, 10));
		return pagina;
	}
	
}
