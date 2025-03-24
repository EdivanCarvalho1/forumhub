package br.edu.iff.ccc.bsi.forumhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.model.Post;
import br.edu.iff.ccc.bsi.forumhub.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Person", description= "Operações relacionadas a posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/post")
	@Operation(summary= "Retorna todos os posts")
	public ResponseEntity<List<Post>> getPosts(){
		
		List<Post> postList = postService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(postList);
		
	}
	
	@GetMapping("/post/{id}")
	@Operation(summary= "Retorna um post pelo ID")
	public ResponseEntity<Post> getPost(@PathParam(value="id") Long id){
		
		Post post = postService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(post);
	}
	@PostMapping("/post")
	@Operation(summary= "Cria um post")
	public ResponseEntity<Void> postPost(@RequestBody Post post){
		
		if(post != null) {
			postService.postPost(post);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/post/{id}")
	@Operation(summary= "Deleta um post pelo ID")
	public ResponseEntity<Void> deletePost(@PathParam(value = "id") Long id){
		
		if(id != null) {
			postService.deletePost(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/post/{id}")
	@Operation(summary= "Atualiza um post pelo ID")
	public ResponseEntity<Void> updatePost(@PathParam(value = "id") Long id, @RequestBody Post post){
		
		if(id != null) {
			postService.updatePost(id, post);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
