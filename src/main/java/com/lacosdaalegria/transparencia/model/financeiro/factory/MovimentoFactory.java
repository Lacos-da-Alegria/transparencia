package com.lacosdaalegria.transparencia.model.financeiro.factory;

import com.lacosdaalegria.transparencia.model.controle.Carga;
import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.RECEITA;
import static com.lacosdaalegria.transparencia.model.dominio.TipoEnum.DESPESA;

import static com.lacosdaalegria.transparencia.model.dominio.StatusEnum.ATIVO;

import java.math.BigDecimal;

import com.lacosdaalegria.transparencia.model.financeiro.Movimento;
import com.lacosdaalegria.transparencia.util.DataUtil;

public abstract class MovimentoFactory {
	
	public static Movimento create(String linha, Carga carga) {
		
		String[] infos = linha.split(";");
		
		Movimento movimento = new Movimento();
		
		movimento.setCarga(carga);
		movimento.setStatus(ATIVO.obj());
		movimento.setData(DataUtil.parseDate(r(infos[1]), "yyyyMMdd"));
		movimento.setDescricao(r(infos[3]));
		movimento.setValor(new BigDecimal(r(infos[4])));
		
		if(r(infos[5]).equals("C"))
			movimento.setTipo(RECEITA.obj());
		else
			movimento.setTipo(DESPESA.obj());
		
		return movimento;
	}
	/**
	 * Remove todas as " de uma String fornecida
	 * @param s
	 * @return
	 */
	private static String r(String s) {
		return s.replaceAll("\"", "");
	}
	

/**
	
	Posições do Arquivo
	0 - Conta
	1 - Data Movimentação yyyyMMdd
	2 - Numero do Doc
	3 - Historico
	4 - Valor
	5 - Debito ou Credito

*/

}
