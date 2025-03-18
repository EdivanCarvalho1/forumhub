package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	PersonService personService;
	
	@Mock
	private PersonRepository personRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Person com sucesso.")
    void testFindById() {
         Person mockedPerson = new Person(1L, "Edivan", "123456789", "123", LocalDateTime.now(), STATUS.ACTIVE, 1);
         when(personRepository.findById(1L)).thenReturn(Optional.of(mockedPerson));
        
        Person result = personService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedPerson.getNickname(), result.getNickname());
        verify(personRepository).findById(1L);
        
    }
    
    @Test
    @DisplayName("Busca por Id em Person com sucesso.")
    void testFindByNickName() {
         Person mockedPerson = new Person(1L, "Edivan", "123456789", "123", LocalDateTime.now(), STATUS.ACTIVE, 1);
         when(personRepository.findByNickname("Edivan"));
        
        Person result = personService.findByNickname("Edivan").orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedPerson.getNickname(), result.getNickname());
        verify(personRepository).findById(1L);
        
    }
    
    @Test
    @DisplayName("Busca por Id em Person com sucesso.")
    void testFindByNicknameAndPhone() {
         Person mockedPerson = new Person(1L, "Edivan", "123456789", "123", LocalDateTime.now(), STATUS.ACTIVE, 1);
         when(personRepository.findByNicknameAndPhone("Edivan", "123"));
        
        List<Person> result = personService.findByNicknameAndPhone("Edivan", "123").orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedPerson.getNickname());
        verify(personRepository).findById(1L);
        
    }

}
