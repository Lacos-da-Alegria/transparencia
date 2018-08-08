package com.lacosdaalegria.transparencia.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lacosdaalegria.transparencia.model.controle.Categoria;
import com.lacosdaalegria.transparencia.model.controle.Item;
import com.lacosdaalegria.transparencia.model.dominio.Tipo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Filtro {
	
	private Date inicio;
	private Date fim;
	
	private List<Item> itens;
	private List<Categoria> categorias;
	
	private BigDecimal maximo;
	private BigDecimal minimo;
	
	private Integer regularidade;
	
	private Tipo tipo;
	
	public boolean vazio() {
		return inicio == null && fim == null && itens == null && 
			   categorias == null && maximo == null && minimo == null && 
			   tipo == null;
	}
	
	public void trataFiltro() {
		if(fim == null)
			fim = new Date();
	}
	
	public boolean isRegular() {
		return regularidade.equals(0);
	}
	
	public boolean isIrregular() {
		return regularidade.equals(1);
	}
	

}
