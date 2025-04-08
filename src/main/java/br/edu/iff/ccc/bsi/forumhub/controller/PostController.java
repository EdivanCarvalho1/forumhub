package br.edu.iff.ccc.bsi.forumhub.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.assembler.PostModel;
import br.edu.iff.ccc.bsi.forumhub.exception.EmptyListException;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidPostException;
import br.edu.iff.ccc.bsi.forumhub.exception.PostNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Post", description = "Operações relacionadas a posts")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private PostModel assembler;

	@GetMapping("/post")
	@Operation(summary= "Retorna todos os posts")
	public ResponseEntity<CollectionModel<EntityModel<Post>>> getPosts(){
		
		List<EntityModel<Post>> postList = postService.findAll()
				.orElseThrow(() -> new PostNotFoundException("Nenhum post cadastrado"))
				.stream()
				.map(assembler::toModel)
                .collect(Collectors.toList());
        
        if (postList.isEmpty()) {
        	throw new EmptyListException("Nenhum post cadastrado");
        }
		
		return ResponseEntity.ok().body(CollectionModel.of(postList));
		
	}

	@GetMapping("/post/{id}")
	@Operation(summary = "Retorna um post pelo ID")
	public ResponseEntity<EntityModel<Post>> getPost(@PathVariable Long id) {

		Post post = postService.findOne(id).orElseThrow(() -> new PostNotFoundException("Post não encontrado!"));
		
		EntityModel<Post> postModel = assembler.toModel(post);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(postModel);
	}

	@PostMapping("/post")
	@Operation(summary = "Cria um post")
	public ResponseEntity<Void> postPost(@RequestBody Post post) {

		if (post != null) {
			postService.postPost(post);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidPostException("Post inválido");
	}

	@DeleteMapping("/post/{id}")
	@Operation(summary = "Deleta um post pelo ID")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {

		if (id != null) {
			postService.deletePost(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new PostNotFoundException("Post não encontrado");
	}

	@PutMapping("/post/{id}")
	@Operation(summary = "Atualiza um post pelo ID")
	public ResponseEntity<Void> updatePost(@PathVariable Long id, @RequestBody Post post) {

		if (id != null && post != null) {
			postService.updatePost(id, post);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new PostNotFoundException("Post não encontrado");
	}
}
