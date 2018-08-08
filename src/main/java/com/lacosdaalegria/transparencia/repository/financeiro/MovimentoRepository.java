package com.lacosdaalegria.transparencia.repository.financeiro;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.dto.HomeDTO;
import com.lacosdaalegria.transparencia.model.financeiro.Movimento;

@Repository
public interface MovimentoRepository extends CrudRepository<Movimento, Long> {
	
	Page<Movimento> findAll(Specification<Movimento> specification, Pageable pageable);
	
	@Query("select new com.lacosdaalegria.transparencia.dto.HomeDTO("
			+ "sum(case when m.tipo = 1 then m.valor else (0 - m.valor) end), "
			+ "(select sum(m1.valor) from Movimento m1 where m1.tipo = 1 and m1.status = 1 and m1.data >= :data), "
			+ "(select sum(m2.valor) from Movimento m2 where m2.tipo = 2 and m2.status = 1 and m2.data >= :data)) "
			+ "from Movimento m where m.status = 1")
	HomeDTO paginaInicial(@Param("data") Date data);

	@Query("select count(m) from Movimento m where m.auditorias IS NOT EMPTY "
		 + "or m.categoria.tag = 'pendente' or (m.tipo = 2 and m.comprovantes IS EMPTY)")
	Integer irregularidades();
	
}
