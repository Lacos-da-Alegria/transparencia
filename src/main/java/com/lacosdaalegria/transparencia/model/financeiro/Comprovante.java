package com.lacosdaalegria.transparencia.model.financeiro;

import static com.lacosdaalegria.transparencia.model.dominio.StatusEnum.ATIVO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lacosdaalegria.transparencia.model.dominio.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Getter @Setter
public class Comprovante {
	
	@Transient
	public static final String URLS3 = "https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-318693850464/";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String imagem;
	
	@NotNull
	@ManyToOne
	private Status status = ATIVO.obj();
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties(value = "comprovantes")
	private Movimento movimento;
	
	
}
