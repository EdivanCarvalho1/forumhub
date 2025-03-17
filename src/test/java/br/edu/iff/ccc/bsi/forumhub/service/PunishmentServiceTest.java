package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Period;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.model.Punishment;
import br.edu.iff.ccc.bsi.forumhub.repository.PunishmentRepository;

@ExtendWith(MockitoExtension.class)
public class PunishmentServiceTest {
	
	@InjectMocks
	PunishmentService punishmentService;
	
	@Mock
	private PunishmentRepository punishmentRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Punishment com sucesso.")
    void testFindById() {
        Punishment mockedPunishment = new Punishment(1L, "1234", Duration.ofDays(7));
        when(punishmentRepository.findById(1L)).thenReturn(Optional.of(mockedPunishment));
        
        Punishment result = punishmentService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedPunishment.getDescription(), result.getDescription());
        verify(punishmentRepository).findById(1L);
        
    }
}
