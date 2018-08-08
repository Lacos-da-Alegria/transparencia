package com.lacosdaalegria.transparencia.model.dominio;

import lombok.Getter;

@Getter
public enum StatusEnum {

	ATIVO(1l, "Ativo"),
	EXCLUIDO(2l, "Excluído"),
	PENDENTE(3l, "Pendente");
	
	private Long id;
	private String nome;
	
	private StatusEnum(Long id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	public Status obj() {
		Status status = new Status();
		status.setId(this.id);
		status.setNome(this.nome);
		return status;
	}
	
}
