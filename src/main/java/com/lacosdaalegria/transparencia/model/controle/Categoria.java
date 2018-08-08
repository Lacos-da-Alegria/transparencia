package com.lacosdaalegria.transparencia.model.controle;

import static com.lacosdaalegria.transparencia.model.dominio.StatusEnum.ATIVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import com.lacosdaalegria.transparencia.model.dominio.Status;
import com.lacosdaalegria.transparencia.model.dominio.Tipo;
import com.lacosdaalegria.transparencia.model.dominio.TipoEnum;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Setter @Getter
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(unique=true)
	private String nome;
	
	@Lob
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String tag;
	
	@NotNull
	@ManyToOne
	private Status status = ATIVO.obj();
	
	@NotNull
	@ManyToOne
	private Tipo tipo;
	
	@Transient
	public boolean isReceita() {
		return tipo.getId().equals(TipoEnum.RECEITA.getId());
	}
	
}
