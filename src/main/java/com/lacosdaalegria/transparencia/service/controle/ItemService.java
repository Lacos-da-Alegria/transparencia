package com.lacosdaalegria.transparencia.service.controle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lacosdaalegria.transparencia.model.controle.Item;
import com.lacosdaalegria.transparencia.repository.controle.ItemRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

	private @NonNull ItemRepository itemRepository;
	
	@Transactional
	public Item save(Item item) {
		return itemRepository.save(item);
	}
	
	public Iterable<Item> all(){
		return itemRepository.findAll();
	}
	
	public Page<Item> page(Integer page){
		
		if(page == null)
			page = 0;
		
		Page<Item> pagina = itemRepository.findAll(PageRequest.of(page, 10));
		return pagina;
	}
	
}
