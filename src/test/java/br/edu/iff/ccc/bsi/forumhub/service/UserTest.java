package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.forumhub.enums.ROLE;
import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import br.edu.iff.ccc.bsi.forumhub.model.User;
import br.edu.iff.ccc.bsi.forumhub.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTest {
	
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testFindById() {
		
		User userTest = new User( 1L, "Edivan", "1234", "2222222", LocalDate.now(), ROLE.USER, STATUS.ACTIVE, 0 );
	
		
		when(userRepository.findById(1L)).thenReturn(Optional.of(userTest));
		
		User result = userService.findUserById(1L).orElseThrow(() -> new NoSuchElementException("Usuário não existente"));
		
		assertNotNull(result);
		assertEquals("Edivan", result.getNickname());
		assertEquals("2222222", result.getPhone());
		verify(userRepository).findById(1L);
		
	}
}
