package com.lacosdaalegria.transparencia.repository.especification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.lacosdaalegria.transparencia.dto.Filtro;
import com.lacosdaalegria.transparencia.model.controle.Categoria;
import com.lacosdaalegria.transparencia.model.controle.Item;
import com.lacosdaalegria.transparencia.model.dominio.TipoEnum;
import com.lacosdaalegria.transparencia.model.financeiro.Movimento;
import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.DESPESA;
import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.RECEITA;

public abstract class MovimentoSpecs {
	
	public static Specification<Movimento> porTipo(TipoEnum tipo) {
        return (root, query, builder) -> builder.
                equal(root.get("categoria").get("tipo"), tipo.getId());
    }
	
	public static Specification<Movimento> filtro(Filtro filtro) {
		return Specifications.where(data(filtro)).and(tipo(filtro)).
				and(itens(filtro)).and(categorias(filtro)).and(irregulares(filtro));
    }
	
	private static Specification<Movimento> tipo(Filtro filtro) {
        return (root, query, builder) -> {
        	if(filtro.getTipo() != null) {
        		return builder.equal(root.get("tipo"), filtro.getTipo().getId());
        	} else {
        		return null;
        	}
        };
                
    }
	
	private static Specification<Movimento> categorias(Filtro filtro){
		if(filtro.getCategorias() != null && !filtro.getCategorias().isEmpty()) {
			return (root, query, builder) -> root.get("categoria").in(listIdCategorias(filtro));
		} 
		return null;
	}
	
	private static Specification<Movimento> itens(Filtro filtro){
		if(filtro.getItens() != null && !filtro.getItens().isEmpty()) {
			return (root, query, builder) -> root.join("itens").in(listIdItens(filtro));
		} 
		return null;
	}
	
	private static Specification<Movimento> data(Filtro filtro){
		return (root, query, builder) -> {
			
			if(filtro.getInicio() != null) {
				if(filtro.getFim() != null) {
					return builder.between(root.get("data"), filtro.getInicio(), filtro.getFim());
				} else {
					return builder.greaterThanOrEqualTo(root.get("data"), filtro.getInicio());
				}
			} else {
				if(filtro.getFim() != null) {
					return builder.lessThanOrEqualTo(root.get("data"), filtro.getFim());
				} else {
					return null;
				}
			}
			
		};
                
	}
	
	private static Specification<Movimento> irregulares(Filtro filtro){
		if(filtro.getRegularidade() != null) {
			if(filtro.isIrregular()) {
				return irregularidade();
			} else {
				return regularidade();
			}
		} else
			return null;
	}
	
	private static Specification<Movimento> irregularidade(){
		return (root, query, builder) -> {
			
			Subquery<Movimento> sq = query.subquery(Movimento.class);
			Root<Movimento> sr = sq.from(Movimento.class);
			
			sq.select(sr).where(builder.equal(sr.joinList("auditorias").get("status"), 3));
			
			return  builder.or(
					builder.equal(root.get("categoria").get("tag"), "pendente"), 
					builder.and(builder.isEmpty(root.get("comprovantes")), builder.equal(root.get("tipo"), DESPESA.getId())),
					builder.exists(sq)
					);
			
		};
	}
	
	
	private static Specification<Movimento> regularidade(){
		return (root, query, builder) -> {
			
			Subquery<Movimento> sq = query.subquery(Movimento.class);
			Root<Movimento> sr = sq.from(Movimento.class);
			
			sq.select(sr).where(builder.equal(sr.joinList("auditorias").get("status"), 3));
			
			return  builder.and(
					builder.notEqual(root.get("categoria").get("tag"), "pendente"), 
					builder.or(builder.isNotEmpty(root.get("comprovantes")), builder.equal(root.get("tipo"), RECEITA.getId())),
					builder.not(builder.exists(sq))
					);
			
		};
	}
	
	private static List<Long> listIdItens(Filtro filtro) {
		
		List<Long> ids = new ArrayList<>();
		
		for(Item i : filtro.getItens()) {
			ids.add(i.getId());
		}
		
		return ids;
	}
	
	private static List<Long> listIdCategorias(Filtro filtro) {
		
		List<Long> ids = new ArrayList<>();
		
		for(Categoria c : filtro.getCategorias()) {
			ids.add(c.getId());
		}
		
		return ids;
	}

}
