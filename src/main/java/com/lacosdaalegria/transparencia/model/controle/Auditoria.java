package com.lacosdaalegria.transparencia.model.controle;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.lacosdaalegria.transparencia.model.dominio.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.lacosdaalegria.transparencia.model.dominio.StatusEnum.PENDENTE;

import java.util.Date;

@Entity
@DynamicUpdate
@Setter @Getter
public class Auditoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date criacao = new Date();
	
	@Lob
	@NotBlank
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Status status = PENDENTE.obj();
	
	@Email
	private String email;
	private String contato;

}
