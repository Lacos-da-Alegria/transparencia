package com.lacosdaalegria.transparencia.repository.access;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lacosdaalegria.transparencia.model.access.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByLogin(String login);

}
