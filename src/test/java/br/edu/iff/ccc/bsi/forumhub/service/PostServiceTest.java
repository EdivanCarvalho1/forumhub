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
import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
	
	@InjectMocks
	PostService postService;
	
	@Mock
	private PostRepository postRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Post com sucesso.")
    void testFindById() {
    	Topic topic = new Topic();
    	Person person = new Person();
        Post mockedPost = new Post(1L, 0, 0, "1234", LocalDateTime.now(), person, topic);
        when(postRepository.findById(1L)).thenReturn(Optional.of(mockedPost));
        
        Post result = postService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedPost.getContent(), result.getContent());
        verify(postRepository).findById(1L);
        
    }
}
