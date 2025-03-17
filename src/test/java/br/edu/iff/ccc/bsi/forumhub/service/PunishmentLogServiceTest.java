package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
import br.edu.iff.ccc.bsi.forumhub.model.PunishmentLog;
import br.edu.iff.ccc.bsi.forumhub.repository.PunishmentLogRepository;

@ExtendWith(MockitoExtension.class)
public class PunishmentLogServiceTest {
	
	@InjectMocks
	PunishmentLogService punishmentLogService;
	
	@Mock
	private PunishmentLogRepository punishmentLogRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Punishment com sucesso.")
    void testFindById() {
    	Person person = new Person();
    	Punishment punishment = new Punishment();
    	LocalDateTime punishmentPeriod = LocalDateTime.now();
    	punishmentPeriod.plusDays(7);
        PunishmentLog mockedPunishment = new PunishmentLog(1L, person, punishment, LocalDateTime.now(), punishmentPeriod );
        when(punishmentLogRepository.findById(1L)).thenReturn(Optional.of(mockedPunishment));
        
        PunishmentLog result = punishmentLogService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedPunishment.getEndDate(), result.getEndDate());
        verify(punishmentLogRepository).findById(1L);
        
    }
}
