package com.lacosdaalegria.transparencia.service.financeiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lacosdaalegria.transparencia.dto.Filtro;
import com.lacosdaalegria.transparencia.dto.HomeDTO;
import com.lacosdaalegria.transparencia.dto.MovimentoDTO;
import com.lacosdaalegria.transparencia.model.dominio.TipoEnum;
import com.lacosdaalegria.transparencia.model.financeiro.Movimento;
import com.lacosdaalegria.transparencia.repository.especification.MovimentoSpecs;
import com.lacosdaalegria.transparencia.repository.financeiro.MovimentoRepository;
import com.lacosdaalegria.transparencia.service.controle.CategoriaService;
import com.lacosdaalegria.transparencia.util.DataUtil;
import com.lacosdaalegria.transparencia.util.NumberUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentoService {
	
	private @NonNull MovimentoRepository movimentoRepository;
	private @NonNull CategoriaService categoriaService;
	private @NonNull ComprovanteService comprovanteService;
	
	public HomeDTO paginaInicial() {
		
		HomeDTO homeDTO = movimentoRepository.paginaInicial(DataUtil.diasAtras(90));
		homeDTO.setIrregularidades(movimentoRepository.irregularidades());
		
		return homeDTO;
	}
	
	public Page<Movimento> page(Integer page, Filtro filtro){
		
		Pageable pageable = PageRequest.of(NumberUtil.treatNull(page), 10, Direction.DESC, "data");
		
		return movimentoRepository.findAll(MovimentoSpecs.filtro(filtro), pageable);
		
	}
	
	@Transactional
	public void saveCarga(Movimento movimento) {
		movimento.setCategoria(categoriaService.findByTag(movimento.getDescricao()));
		movimentoRepository.save(movimento);
	}
	
	public Page<Movimento> page(Integer page, TipoEnum tipo){
		
		return movimentoRepository.findAll(
					MovimentoSpecs.porTipo(tipo),
					PageRequest.of(NumberUtil.treatNull(page), 10));
		
	}
	
	@Transactional
	public Movimento update(MovimentoDTO movimentoDTO) {
		
		Movimento movimento = movimentoRepository.findById(movimentoDTO.getId()).get();
		
		movimento.setItens(movimentoDTO.getItens());
		
		if(validaTipoCategoria(movimento, movimentoDTO))
			movimento.setCategoria(movimentoDTO.getCategoria());
		
		if(movimentoDTO.novaDescricao()) {
			movimento.setDescricao(movimentoDTO.getDescricao());
		}
		
		if(movimentoDTO.temComprovantes()) {
			comprovanteService.save(movimentoDTO.getComprovantes(), movimento);
		}
		
		return movimentoRepository.save(movimento);
	}
	
	private boolean validaTipoCategoria(Movimento movimento, MovimentoDTO movimentoDTO) {
		return movimentoDTO.getCategoria() != null && 
			   movimento.getTipo().getId().equals(movimentoDTO.getCategoria().getTipo().getId());
	}

	
	/**
	public Movimento save(Movimento movimento, MultipartFile[] comprovantes) {
		
		movimento.setComprovante(comprovanteService.save(comprovantes, movimento.isReceita() ? "REC" : "DES"));
		movimento.setUser(userService.getLoggedUser());
		
		return movimentoRepository.save(movimento);
		
	}
	public boolean valid(Movimento m) {
		return m.getValor() != null && m.getCategoria() != null && 
				m.getData() != null;
	}
	*/
	

}
