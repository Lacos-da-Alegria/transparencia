package com.lacosdaalegria.transparencia.service.access;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lacosdaalegria.transparencia.model.access.Role;
import com.lacosdaalegria.transparencia.model.access.User;
import com.lacosdaalegria.transparencia.repository.access.UserRepository;
import com.lacosdaalegria.transparencia.util.SecurityUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private @NonNull UserRepository userRepository;
	private @NonNull RoleService roleService;
	private @NonNull BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User save(User user) {
		
		user.setSenha(bCryptPasswordEncoder.encode(user.getSenha()));
		user.setRoles(roleAnalista());
		
		return this.userRepository.save(user);
	}
	
	public User getLoggedUser() {
		return userRepository.findByLogin(SecurityUtil.loggedUser());
	}
	
	private Set<Role> roleAnalista(){
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(roleService.findRole("ROLE_ANALISTA"));
		
		return roles;
	}
	
	/**
	 * Metodo utilizada para desenvolvimento
	 * @param user
	 */
	@Transactional
	public void addAdmin(User user) {
		
		user = save(user);
		
		user.getRoles().add(roleService.findRole("ROLE_ADMIN"));
		
	}
	
}
