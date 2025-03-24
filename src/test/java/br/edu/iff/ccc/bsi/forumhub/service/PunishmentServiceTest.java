package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
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

import br.edu.iff.ccc.bsi.forumhub.model.Punishment;
import br.edu.iff.ccc.bsi.forumhub.repository.PunishmentRepository;

@ExtendWith(MockitoExtension.class)
public class PunishmentServiceTest {

	@InjectMocks
	private PunishmentService punishmentService;

	@Mock
	private PunishmentRepository punishmentRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas as punições com sucesso.")
	public void testFindAll() {
		List<Punishment> mockedPunishments = punishmentService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma punição encontrada!"));
		when(punishmentRepository.findAll()).thenReturn(mockedPunishments);

		List<Punishment> result = punishmentService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma punição encontrada!"));

		assertNotNull(result);
		assertEquals(mockedPunishments.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em Punishment com sucesso.")
	public void testFindOne() {
		Punishment mockedPunishment = new Punishment(1L, "1234", Duration.ofDays(7));
		when(punishmentRepository.findById(1L)).thenReturn(Optional.of(mockedPunishment));

		Punishment result = punishmentService.findOne(1L)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedPunishment.getDescription(), result.getDescription());
		verify(punishmentRepository).findById(1L);

	}

	@Test
	@DisplayName("Salva uma punição com sucesso.")
	public void postPunishment() {
		Punishment mockedPunishment = new Punishment(1L, "1234", Duration.ofDays(7));
		when(punishmentRepository.save(mockedPunishment)).thenReturn(mockedPunishment);

		punishmentService.postPunishment(mockedPunishment);

		verify(punishmentRepository).save(mockedPunishment);

	}

	@Test
	@DisplayName("Deleta uma punição com sucesso.")
	public void testDeletePunishment() {
		Punishment mockedPunishment = new Punishment(1L, "1234", Duration.ofDays(7));
		punishmentService.deletePunishment(mockedPunishment.getId());
		verify(punishmentRepository).deleteById(mockedPunishment.getId());
	}

	@Test
	@DisplayName("Atualiza uma punição com sucesso.")
	public void testUpdatePunishment() {
	    
		Punishment existingPunishment = new Punishment(1L, "1234", Duration.ofDays(7));
		Punishment updatedPunishment = new Punishment(1L, "1234", Duration.ofDays(7));
		when(punishmentRepository.save(existingPunishment)).thenReturn(updatedPunishment);
		when(punishmentRepository.findById(1L)).thenReturn(Optional.of(existingPunishment));
		
		punishmentService.updatePunishment(1L, updatedPunishment);
		
		verify(punishmentRepository).save(updatedPunishment);
		verify(punishmentRepository).findById(1L);
	}
}
