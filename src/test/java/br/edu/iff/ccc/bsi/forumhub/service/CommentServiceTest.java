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

import br.edu.iff.ccc.bsi.forumhub.model.Comment;
import br.edu.iff.ccc.bsi.forumhub.repository.CommentRepository;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
	@InjectMocks
	CommentService commentService;
	
	@Mock
	private CommentRepository commentRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Comment com sucesso.")
    void testFindById() {
         Comment mockedComment = new Comment(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null );
         when(commentRepository.findById(1L)).thenReturn(Optional.of(mockedComment));
        
        Comment result = commentService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedComment.getDislikes(), result.getDislikes());
        verify(commentRepository).findById(1L);
    }
    
    @Test
    @DisplayName("Busca por Content em Comment com sucesso.")
    void testFindByContent() {
         Comment mockedComment = new Comment(1L, null, 0, 0, "12345", LocalDateTime.now(), null, null );
         when(commentRepository.findByContent("12345")).thenReturn(Optional.of(mockedComment));
        
        Comment result = commentService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedComment.getDislikes(), result.getDislikes());
        verify(commentRepository).findById(1L);
    }
    
    
}
