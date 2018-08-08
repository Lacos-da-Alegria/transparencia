package com.lacosdaalegria.transparencia.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.lacosdaalegria.transparencia.util.DataUtil;
import com.lacosdaalegria.transparencia.util.NumberUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class HomeDTO {
	
	private BigDecimal caixa;
	private BigDecimal receita;
	private BigDecimal despesa;
	private Integer irregularidades;
	
	private Date ultimoMes = DataUtil.diasAtras(90);
	
	public HomeDTO(BigDecimal caixa, BigDecimal receita, BigDecimal despesa) {
		this.caixa = NumberUtil.treatNull(caixa);
		this.receita = NumberUtil.treatNull(receita);
		this.despesa = NumberUtil.treatNull(despesa);
	}

}
