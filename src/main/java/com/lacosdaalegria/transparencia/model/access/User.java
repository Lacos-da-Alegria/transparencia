package com.lacosdaalegria.transparencia.model.access;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@DynamicUpdate
@Setter @Getter
public class User {

	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;

	@Column(unique=true)
	@NotBlank(message = "Forneça um login")
	private String login;

	@NotBlank(message = "Forneça uma senha")
	@Length(min = 6, message = "Sua senha deve conter no mínimo 6 caracteres")
	private String senha;

	@Column(unique=true)
	@NotBlank(message = "Forneça um email")
	@Email(message="Forneça um email válido")
	private String email;

	@Column(unique=true)
	@NotBlank(message = "Forneça um login")
	private String contato;
	
	private Date dtCriacao = new Date();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Role> roles;


}
