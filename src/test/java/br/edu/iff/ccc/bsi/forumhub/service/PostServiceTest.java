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

import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

	@InjectMocks
	private PostService postService;

	@Mock
	private PostRepository postRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas os posts com sucesso.")
	public void testFindAll() {
		List<Post> listPosts = postService.findAll().orElseThrow(() -> new RuntimeException("Nenhum post encontrado!"));
		when(postRepository.findAll()).thenReturn(listPosts);

		List<Post> result = postService.findAll().orElseThrow(() -> new RuntimeException("Nenhum post encontrado!"));

		assertNotNull(result);
		assertEquals(listPosts.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em Post com sucesso.")
	public void testFindOne() {
		Topic topic = new Topic();
		Person person = new Person();
		Post mockedPost = new Post(1L, 0, 0, "1234", LocalDateTime.now(), person, topic);
		when(postRepository.findById(1L)).thenReturn(Optional.of(mockedPost));

		Post result = postService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedPost.getContent(), result.getContent());
		verify(postRepository).findById(1L);
	}

	@Test
	@DisplayName("Criar um post com sucesso.")
	public void testPostPost() {
		Topic topic = new Topic();
		Person person = new Person();
		Post mockedPost = new Post(1L, 0, 0, "1234", LocalDateTime.now(), person, topic);
		when(postRepository.save(mockedPost)).thenReturn(mockedPost);

		postService.postPost(mockedPost);

		verify(postRepository).save(mockedPost);
	}

	@Test
	@DisplayName("Deleta um post com sucesso.")
	public void testDeletePost() {
		postService.deletePost(1L);
		verify(postRepository).deleteById(1L);
	}

	@Test
	@DisplayName("Atualiza um post com sucesso.")
	public void testUpdatePost() {
		Topic topic = new Topic();
		Person person = new Person();
		
		Post existingPost = new Post(1L, 0, 0, "1234", LocalDateTime.now(), person, topic);
		Post updatedPost = new Post(1L, 0, 0, "1234", LocalDateTime.now(), person, topic);

		when(postRepository.findById(1L)).thenReturn(Optional.of(existingPost));
		when(postRepository.save(updatedPost)).thenReturn(updatedPost);
		
		postService.updatePost(1L, updatedPost);
		
		verify(postRepository).save(updatedPost);
	}
}
