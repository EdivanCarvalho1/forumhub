package br.edu.iff.ccc.bsi.forumhub.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.model.Post;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Postagem Controller", description = "Crud em Postagens")
public class PostagemController {

	@GetMapping("/postagem")
	@Operation(summary = "Retorna todas as postagens")
	public ResponseEntity<List<Post>> getPostagem() {
		List<Post> listaPostagem = new ArrayList<>();
		return ResponseEntity.ok().body(listaPostagem);
	}

	@GetMapping("/postagem/{id}")
	@Operation(summary = "Retorna uma postagem por id")
	public ResponseEntity<Post> getPostagemById(@PathParam("id") int id) {
		if (id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Post postagem = new Post();
		return ResponseEntity.ok().body(postagem);
	}

	@PostMapping("/postagem")
	@Operation(summary = "Adiciona uma nova postagem")
	public ResponseEntity<Void> postPostagem(@RequestBody Post postagem) {
		if (postagem == null) {
			return ResponseEntity.badRequest().build();
		}
		Post novaPostagem = postagem;

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/postagem")
	@Operation(summary = "Atualiza uma postagem")
	public ResponseEntity<Void> updatePostagem(@RequestBody Post postagem) {
		if (postagem == null) {
			return ResponseEntity.badRequest().build();
		}
		Post novaPostagem = postagem;

		return ResponseEntity.noContent().build();
	}
}
