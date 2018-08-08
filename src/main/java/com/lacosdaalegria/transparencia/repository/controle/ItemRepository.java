package com.lacosdaalegria.transparencia.repository.controle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.model.controle.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
	Page<Item> findAll(Pageable pageRequest);

}
