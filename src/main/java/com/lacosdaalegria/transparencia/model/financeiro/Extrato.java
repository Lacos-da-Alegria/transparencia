package com.lacosdaalegria.transparencia.model.financeiro;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.lacosdaalegria.transparencia.model.dominio.Status;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Setter @Getter
public class Extrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @NotNull
	private String extrato;

    @NotNull
	private Date criacao = new Date();
	
    @NotNull
	@ManyToOne
	private Status status;
}
