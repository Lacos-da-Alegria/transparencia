package com.lacosdaalegria.transparencia.util;

import java.math.BigDecimal;

public abstract class NumberUtil {

	public static BigDecimal treatNull(BigDecimal valor) {
		if(valor == null)
			return BigDecimal.ZERO;
		else 
			return valor;
	}
	
	public static Integer treatNull(Integer valor) {
		if(valor == null)
			return 0;
		else 
			return valor;
	}
	
}
