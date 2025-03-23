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

import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

	@InjectMocks
	private RoleService roleService;

	@Mock
	private RoleRepository roleRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas as roles com sucesso.")
	public void testFindAll() {
		List<Role> listRoles = roleService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma role encontrada!"));
		when(roleRepository.findAll()).thenReturn(listRoles);

		List<Role> result = roleService.findAll().orElseThrow(() -> new RuntimeException("Nenhuma role encontrada!"));

		assertNotNull(result);
		assertEquals(listRoles.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em Role com sucesso.")
	public void testFindOne() {
		Role mockedRole = new Role(1L, "ROLE_USER", "Usuário padrão");
		when(roleRepository.findById(1L)).thenReturn(java.util.Optional.of(mockedRole));

		Role result = roleService.findOne(1L).orElseThrow(() -> new RuntimeException("Role não encontrada!"));

		assertNotNull(result);
		assertEquals(mockedRole.getId(), result.getId());
		assertEquals(mockedRole.getRoleName(), result.getRoleName());
		verify(roleRepository).findById(1L);
	}

	@Test
	@DisplayName("Salva uma nova Role com sucesso.")
	public void testSave() {
		Role mockedRole = new Role(1L, "ROLE_USER", "Usuário padrão");
		when(roleRepository.save(mockedRole)).thenReturn(mockedRole);

		roleRepository.save(mockedRole);

		verify(roleRepository).save(mockedRole);
	}

	@Test
	@DisplayName("Deleta uma Role com sucesso.")
	public void testDelete() {
		Role mockedRole = new Role(1L, "ROLE_USER", "Usuário padrão");

		roleService.deleteRole(mockedRole.getId());

		verify(roleRepository).deleteById(mockedRole.getId());
	}

	@Test
	@DisplayName("Atualiza uma Role com sucesso.")
	public void testUpdate() {
		Role existingRole = new Role(1L, "ROLE_USER", "Usuário padrão");
		Role updatedRole = new Role(1L, "ROLE_ADMIN", "Administrador");
		when(roleRepository.save(existingRole)).thenReturn(updatedRole);
		when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
		
		roleService.updateRole(existingRole.getId(), updatedRole);
		
		verify(roleRepository).save(updatedRole);
		verify(roleRepository).findById(1L);
	}
}
