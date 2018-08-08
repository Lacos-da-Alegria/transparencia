package com.lacosdaalegria.transparencia.repository.controle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.model.controle.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
	
	Page<Categoria> findAll(Pageable pageRequest);
	
	Categoria findByTag(String tag);

}
