package com.lacosdaalegria.transparencia.repository.financeiro;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.model.financeiro.Comprovante;

@Repository
public interface ComprovanteRepository extends CrudRepository<Comprovante, Long> {

}
