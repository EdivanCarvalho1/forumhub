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

import br.edu.iff.ccc.bsi.forumhub.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Categoria Controller", description = "CRUD em Categoria")
public class CategoriaController {

	@GetMapping("/categoria")
	@Operation(summary = "Retorna todas as categorias")
	public ResponseEntity<List<Category>> getCategoria() {
		List<Category> listaCategoria = new ArrayList<>();

		return ResponseEntity.ok().body(listaCategoria);
	}

	@GetMapping("/categoria/{id}")
	@Operation(summary = "Retorna uma categoria por id")
	public ResponseEntity<Category> getCategoriaById(@PathParam("id") int id) {
		if (id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Category categoria = new Category();
		return ResponseEntity.ok().body(categoria);
	}

	@PostMapping("/categoria")
	@Operation(summary = "Adiciona uma nova categoria")
	public ResponseEntity<Void> postCategoria(@RequestBody Category categoria) {
		if (categoria == null) {
			return ResponseEntity.badRequest().build();
		}
		Category novaCategoria = categoria;

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/categoria")
	@Operation(summary = "Atualiza uma categoria")
	public ResponseEntity<Void> updateCategoria(@RequestBody Category categoria) {
		if (categoria == null) {
			return ResponseEntity.badRequest().build();
		}
		Category novaCategoria = categoria;

		return ResponseEntity.noContent().build();
	}
}
