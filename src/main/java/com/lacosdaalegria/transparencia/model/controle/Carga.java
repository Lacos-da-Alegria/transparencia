package com.lacosdaalegria.transparencia.model.controle;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.lacosdaalegria.transparencia.model.access.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Setter @Getter
public class Carga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date criacao = new Date();
	
	@NotNull
	@ManyToOne
	private User user;
	
	@NotNull
	@Column(unique=true)
	private String mesAno;

}
