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

import br.edu.iff.ccc.bsi.forumhub.model.Reply;
import br.edu.iff.ccc.bsi.forumhub.repository.ReplyRepository;

@ExtendWith(MockitoExtension.class)
public class ReplyServiceTest {
	@InjectMocks
	ReplyService replyService;
	
	@Mock
	private ReplyRepository replyRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Reply com sucesso.")
    void testFindById() {
         Reply mockedReply = new Reply(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null );
         when(replyRepository.findById(1L)).thenReturn(Optional.of(mockedReply));
        
        Reply result = replyService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedReply.getDislikes(), result.getDislikes());
        verify(replyRepository).findById(1L);
        
    }
}
