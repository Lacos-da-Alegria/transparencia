package com.lacosdaalegria.transparencia.repository.controle;

import org.springframework.data.repository.CrudRepository;

import com.lacosdaalegria.transparencia.model.controle.Carga;

public interface CargaRepository extends CrudRepository<Carga, Long>{
	
	boolean existsByMesAno(String mesAno);

}
