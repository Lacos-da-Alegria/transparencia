package com.lacosdaalegria.transparencia.model.financeiro;

import static com.lacosdaalegria.transparencia.model.dominio.StatusEnum.ATIVO;
import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.DESPESA;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import com.lacosdaalegria.transparencia.model.controle.Auditoria;
import com.lacosdaalegria.transparencia.model.controle.Carga;
import com.lacosdaalegria.transparencia.model.controle.Categoria;
import com.lacosdaalegria.transparencia.model.controle.Item;
import com.lacosdaalegria.transparencia.model.dominio.Status;
import com.lacosdaalegria.transparencia.model.dominio.Tipo;

import lombok.Getter;
import lombok.Setter;

@Entity
@DynamicUpdate
@Setter @Getter
public class Movimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private Date criacao = new Date();
	
	@NotNull
	@ManyToOne
	private Status status = ATIVO.obj();
	
	@NotNull
	private Date data;
	
	@OneToMany(mappedBy = "movimento")
	private List<Comprovante> comprovantes;
	
	@OneToMany
	private List<Auditoria> auditorias;
	
	@NotNull
	@ManyToOne
	private Categoria categoria;
	
	@NotNull
	@ManyToOne
	private Tipo tipo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Item> itens;
	
	@NotNull
	@ManyToOne
	private Carga carga;
	
	@Lob
	private String descricao;
	
	@Transient
	public boolean isReceita() {
		return categoria.isReceita();
	}
	
	@Transient
	public String irregularidades() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<ul>");
		
		if(auditorias != null && !auditorias.isEmpty()) {
			sb.append("<li>" + auditorias.size() + " Auditoria(s) aberta(s)! </li>");
		}
		
		if(categoria.getTag().equals("pendente")) {
			sb.append("<li> Categoria ainda não indicada. </li>");
		}
		
		if(tipo.getId().equals(DESPESA.getId()) && (comprovantes == null || comprovantes.isEmpty())) {
			sb.append("<li> Despesa sem comprovantes.</li>");
		}
		
		if(sb.toString().equals("<ul>"))
			sb.append("<li> Sem irregularidades <i class=\"icon-happy2\"></i> </li>");
		
		sb.append("</ul>");
		
		return sb.toString();
	}
	
	@Transient
	public boolean isIrregular() {
		return (auditorias != null && !auditorias.isEmpty()) || categoria.getTag().equals("pendente") || 
				(tipo.getId().equals(DESPESA.getId()) && (comprovantes == null || comprovantes.isEmpty()));
	}

}
