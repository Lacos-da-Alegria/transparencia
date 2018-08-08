package com.lacosdaalegria.transparencia.model.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Status {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	public String tag() {
		if(id.equals(1l)) {
			return "tag-success";
		} else {
			if(id.equals(2l)) {
				return "tag-danger";
			} else {
				return "tag-warning";
			}
		}
	}

}
