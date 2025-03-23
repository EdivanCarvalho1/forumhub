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

import br.edu.iff.ccc.bsi.forumhub.model.Reply;
import br.edu.iff.ccc.bsi.forumhub.repository.ReplyRepository;

@ExtendWith(MockitoExtension.class)
public class ReplyServiceTest {

	@InjectMocks
	private ReplyService replyService;

	@Mock
	private ReplyRepository replyRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas as respostas com sucesso.")
	public void testFindAll() {
		List<Reply> mockedReplies = replyService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma resposta encontrada!"));
		when(replyRepository.findAll()).thenReturn(mockedReplies);

		List<Reply> result = replyService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma resposta encontrada!"));

		assertNotNull(result);
		assertEquals(mockedReplies.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em Reply com sucesso.")
	public void testFindOne() {
		Reply mockedReply = new Reply(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null);
		when(replyRepository.findById(1L)).thenReturn(Optional.of(mockedReply));

		Reply result = replyService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedReply.getDislikes(), result.getDislikes());
		verify(replyRepository).findById(1L);
	}

	@Test
	@DisplayName("Cria uma resposta com sucesso.")
	public void testPostReply() {
		Reply mockedReply = new Reply(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null);
		when(replyRepository.save(mockedReply)).thenReturn(mockedReply);

		replyService.postReply(mockedReply);

		verify(replyRepository).save(mockedReply);
	}

	@Test
	@DisplayName("Delete uma resposta com sucesso.")
	public void testDeleteReply() {
		Reply mockedReply = new Reply(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null);

		replyService.deleteReply(1L);

		verify(replyRepository).deleteById(1L);
	}

	@Test
	@DisplayName("Atualiza uma resposta com sucesso.")
	public void testUpdateReply() {
		
		Reply existingReply = new Reply(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null);
		Reply updatedReply = new Reply(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null);
		when(replyRepository.findById(1L)).thenReturn(Optional.of(existingReply));
		when(replyRepository.save(updatedReply)).thenReturn(updatedReply);
		
		replyService.updateReply(1L, updatedReply);
		
		verify(replyRepository).findById(1L);
		verify(replyRepository).save(updatedReply);
	}

}
