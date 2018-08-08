package com.lacosdaalegria.transparencia.repository.access;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.model.access.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

	Role findByRole(String role);
	
}
