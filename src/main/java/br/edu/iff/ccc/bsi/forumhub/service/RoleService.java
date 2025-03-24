package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Optional<List<Role>> findAll() {

		return Optional.ofNullable(roleRepository.findAll());

	}

	public Optional<Role> findOne(Long id) {

		Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));

		return Optional.ofNullable(role);

	}

	public void postRole(Role role) {

		roleRepository.save(role);

	}

	public void deleteRole(Long id) {
		roleRepository.deleteById(id);
	}

	public void updateRole(Long id, Role updatedRole) {
	   Role existingRole = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
	   
	   existingRole.setDescription(updatedRole.getDescription());
	   existingRole.setRoleName(updatedRole.getRoleName());
	   
	   roleRepository.save(existingRole);
	}
}
