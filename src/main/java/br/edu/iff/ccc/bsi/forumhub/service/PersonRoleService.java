package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.PersonRole;
import br.edu.iff.ccc.bsi.forumhub.repository.PersonRoleRepository;

@Service
public class PersonRoleService {

	@Autowired
	PersonRoleRepository personRoleRepository;

	public Optional<List<PersonRole>> findAll() {

		return Optional.ofNullable(personRoleRepository.findAll());

	}

	public Optional<PersonRole> findOne(Long id) {

		PersonRole personRole = personRoleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("O comentário não existe"));

		return Optional.ofNullable(personRole);

	}

	public void postPersonRole(PersonRole personRole) {

		personRoleRepository.save(personRole);

	}

	public void deletePersonRole(Long id) {
		personRoleRepository.deleteById(id);
	}

	public void updatePersonRole(Long id, PersonRole updatedPersonRole) {

		PersonRole existingPersonRole = personRoleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("PersonRole not found"));
		
		existingPersonRole.setPerson(updatedPersonRole.getPerson());
		existingPersonRole.setRole(updatedPersonRole.getRole());
		
		personRoleRepository.save(existingPersonRole);
	}
}
