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

import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.repository.TopicRepository;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

	@InjectMocks
	private TopicService topicService;

	@Mock
	private TopicRepository topicRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas os tópicos com sucesso.")
	public void testFindAll() {
		List<Topic> listTopics = topicService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhum tópico encontrado!"));
		when(topicRepository.findAll()).thenReturn(listTopics);
		
		List<Topic> result = topicService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhum tópico encontrado!"));
		
		assertNotNull(result);
		assertEquals(listTopics.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em Topic com sucesso.")
	public void testFindOne() {
		Category category = new Category();
		Topic mockedTopic = new Topic(1L, "1234", LocalDateTime.now(), category);
		when(topicRepository.findById(1L)).thenReturn(Optional.of(mockedTopic));

		Topic result = topicService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedTopic.getTitle(), result.getTitle());
		verify(topicRepository).findById(1L);

	}
	@Test
	@DisplayName("Cria um tópico com sucesso.")
	public void testPostTopic() {
		Category category = new Category();
		Topic mockedTopic = new Topic(1L, "1234", LocalDateTime.now(), category);
		when(topicRepository.save(mockedTopic)).thenReturn(mockedTopic);

		topicService.postTopic(mockedTopic);

		verify(topicRepository).save(mockedTopic);
	}
	@Test()
	@DisplayName("Deleta um tópico com sucesso.")
	public void testDeleteTopic() {
		Category category = new Category();
		Topic mockedTopic = new Topic(1L, "1234", LocalDateTime.now(), category);

		topicService.deleteTopic(1L);

		verify(topicRepository).deleteById(1L);
	}
	@Test()
	@DisplayName("Atualiza um tópico com sucesso.")
	public void testUpdateTopic() {
		Category category = new Category();
		Topic existingTopic = new Topic(1L, "1234", LocalDateTime.now(), category);
		Topic updatedTopic = new Topic(1L, "1235", LocalDateTime.now(), category);

		when(topicRepository.findById(1L)).thenReturn(Optional.of(existingTopic));
		when(topicRepository.save(updatedTopic)).thenReturn(updatedTopic);
		
		topicService.updateTopic(1L, updatedTopic);
		
		verify(topicRepository).save(updatedTopic);
		verify(topicRepository).findById(1L);
	}
}
