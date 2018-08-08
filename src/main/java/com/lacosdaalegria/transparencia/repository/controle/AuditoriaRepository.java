package com.lacosdaalegria.transparencia.repository.controle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.model.controle.Auditoria;

@Repository
public interface AuditoriaRepository extends CrudRepository<Auditoria, Long>{
	
}
