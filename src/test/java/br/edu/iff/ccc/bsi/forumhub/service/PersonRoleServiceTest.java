package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.forumhub.model.PersonRole;
import br.edu.iff.ccc.bsi.forumhub.repository.PersonRoleRepository;

@ExtendWith(MockitoExtension.class)
public class PersonRoleServiceTest {

	@InjectMocks
	PersonRoleService personRoleService;

	@Mock
	PersonRoleRepository personRoleRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas as PersonRoles com sucesso.")
	public void testFindAll() {
		List<PersonRole> listPersonRoles = personRoleService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma PersonRole encontrada!"));
		when(personRoleRepository.findAll()).thenReturn(listPersonRoles);

		List<PersonRole> result = personRoleService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma PersonRole encontrada!"));

		assertNotNull(result);
		assertEquals(listPersonRoles.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em PersonRole com sucesso.")
	public void testFindOne() {
		PersonRole mockedPersonRole = new PersonRole(1L, null, null);
		when(personRoleRepository.findById(1L)).thenReturn(java.util.Optional.of(mockedPersonRole));

		PersonRole result = personRoleService.findOne(1L)
				.orElseThrow(() -> new RuntimeException("PersonRole não encontrada!"));

		assertNotNull(result);
		assertEquals(mockedPersonRole.getId(), result.getId());
		assertEquals(mockedPersonRole.getPerson(), result.getPerson());
		assertEquals(mockedPersonRole.getRole(), result.getRole());
		verify(personRoleRepository).findById(mockedPersonRole.getId());
	}

	@Test
	@DisplayName("Cria PersonRole com sucesso.")
	public void testPostPersonRole() {
		PersonRole mockedPersonRole = new PersonRole(1L, null, null);
		personRoleService.postPersonRole(mockedPersonRole);
		verify(personRoleRepository).save(mockedPersonRole);
	}

	@Test
	@DisplayName("Deleta PersonRole com sucesso.")
	public void testDeletePersonRole() {
		personRoleService.deletePersonRole(1L);
		verify(personRoleRepository).deleteById(1L);
	}

	@Test
	@DisplayName("Atualiza PersonRole com sucesso.")
	public void testUpdatePersonRole() {
		
		PersonRole existingPersonRole = new PersonRole(1L, null, null);
		
		PersonRole updatedPersonRole = new PersonRole(1L, null, null);
		when(personRoleRepository.save(existingPersonRole)).thenReturn(updatedPersonRole);
		when(personRoleRepository.findById(1L)).thenReturn(Optional.of(existingPersonRole));
		
		personRoleService.updatePersonRole(existingPersonRole.getId(), updatedPersonRole);
		
		verify(personRoleRepository).save(updatedPersonRole);
	}
}
