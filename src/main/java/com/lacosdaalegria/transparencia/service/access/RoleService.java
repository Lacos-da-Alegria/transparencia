package com.lacosdaalegria.transparencia.service.access;

import org.springframework.stereotype.Service;

import com.lacosdaalegria.transparencia.model.access.Role;
import com.lacosdaalegria.transparencia.repository.access.RoleRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private @NonNull RoleRepository roleRepository;
	
	public Role findRole(String role) {
		return roleRepository.findByRole(role);
	}
	
}
