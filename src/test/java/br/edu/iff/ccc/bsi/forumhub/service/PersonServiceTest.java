package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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

import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.repository.PersonRepository;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas as pessoas com sucesso.")
	public void testFindAll() {
		List<Person> mockedPerson = List.of(new Person(1L, "Edivan", "123456789", "123", "edivan@email.com",
				LocalDateTime.now(), STATUS.ACTIVE, 1));
		when(personRepository.findAll()).thenReturn(mockedPerson);

		List<Person> result = personService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma pessoa encontrada!"));

		assertNotNull(result);
		assertEquals(mockedPerson.size(), result.size());
		verify(personRepository).findAll();
	}

	@Test
	@DisplayName("Busca por Id em Person com sucesso.")
	public void testFindOne() {
		Person mockedPerson = new Person(1L, "Edivan", "123456789", "123", "edivan@email.com", LocalDateTime.now(),
				STATUS.ACTIVE, 1);
		when(personRepository.findById(1L)).thenReturn(Optional.of(mockedPerson));

		Person result = personService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedPerson.getNickname(), result.getNickname());
		verify(personRepository).findById(1L);

	}

	@Test
	@DisplayName("Busca por Id em Person com sucesso.")
	public void testFindByNickName() {
		Person mockedPerson = new Person(1L, "Edivan", "123456789", "123", "edivan@email.com", LocalDateTime.now(),
				STATUS.ACTIVE, 1);
		when(personRepository.findByNickname("Edivan")).thenReturn(Optional.of(mockedPerson));

		Person result = personService.findByNickname("Edivan")
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedPerson.getNickname(), result.getNickname());
		verify(personRepository).findByNickname("Edivan");

	}

	@Test
	@DisplayName("Busca por Nickname e Phone em Person com sucesso.")
	public void testFindByNicknameAndPhone() {

		List<Person> mockedPerson = List.of(new Person(1L, "Edivan", "123456789", "123", "edivan@email.com",
				LocalDateTime.now(), STATUS.ACTIVE, 1));

		when(personRepository.findByNicknameAndPhone("Edivan", "123")).thenReturn(Optional.of(mockedPerson));

		List<Person> result = personService.findByNicknameAndPhone("Edivan", "123")
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(mockedPerson.get(0).getNickname(), result.get(0).getNickname());

		verify(personRepository).findByNicknameAndPhone("Edivan", "123");
	}

	@Test
	@DisplayName("Cria pessoa com sucesso.")
	public void testPostPerson() {
		Person mockedPerson = new Person(1L, "Edivan", "123456789", "123", "edivan@email.com", LocalDateTime.now(),
				STATUS.ACTIVE, 1);
		personService.postPerson(mockedPerson);

		verify(personRepository).save(mockedPerson);
	}

	@Test
	@DisplayName("Deleta pessoa com sucesso.")
	public void testDeletePerson() {
		personService.deletePerson(1L);

		verify(personRepository).deleteById(1L);
	}

	@Test
	@DisplayName("Atualiza uma pessoa com sucesso.")
	public void testUpdatePerson() {
		Person existingPerson = new Person(1L, "Edivan", "123456789", "123", "edivan@email.com", LocalDateTime.now(),
				STATUS.ACTIVE, 1);
		Person updatedPerson = new Person(1L, "Edivan", "123456789", "123", "123@email.com", LocalDateTime.now(),
				STATUS.ACTIVE, 1);

		when(personRepository.findById(1L)).thenReturn(Optional.of(existingPerson));
		when(personRepository.save(existingPerson)).thenReturn(existingPerson);

		personService.updatePerson(1L, updatedPerson);

		verify(personRepository).findById(1L);
		verify(personRepository).save(updatedPerson);
	}

}
