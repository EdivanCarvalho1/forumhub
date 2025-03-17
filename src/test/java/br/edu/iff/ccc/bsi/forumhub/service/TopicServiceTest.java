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

import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.repository.TopicRepository;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {
	@InjectMocks
	TopicService topicService;
	
	@Mock
	private TopicRepository topicRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Topic com sucesso.")
    void testFindById() {
    	Category category = new Category();
        Topic mockedTopic = new Topic(1L, "1234", LocalDateTime.now(), category);
        when(topicRepository.findById(1L)).thenReturn(Optional.of(mockedTopic));
        
        Topic result = topicService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedTopic.getTitle(), result.getTitle());
        verify(topicRepository).findById(1L);
        
    }
}
