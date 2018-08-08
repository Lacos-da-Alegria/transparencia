package com.lacosdaalegria.transparencia.model.dominio;

import lombok.Getter;

@Getter
public enum TipoEnum {

	RECEITA(1l, "Receita"),
	DESPESA(2l, "Despesa");
	
	private Long id;
	private String nome;
	
	private TipoEnum(Long id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	public Tipo obj() {
		Tipo tipo = new Tipo();
		tipo.setId(this.id);
		tipo.setNome(this.nome);
		return tipo;
	}
	
}
