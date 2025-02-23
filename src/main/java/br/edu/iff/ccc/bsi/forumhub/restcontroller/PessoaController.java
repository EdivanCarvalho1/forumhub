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

import br.edu.iff.ccc.bsi.forumhub.model.Pessoa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Pessoa Controller", description = "Crud em Pessoa")
public class PessoaController {

	@GetMapping("/pessoa")
	@Operation(summary = "Retorna todas as pessoas")
	public ResponseEntity<List<Pessoa>> getPessoa() {
		List<Pessoa> listaPessoa = new ArrayList<>();

		return ResponseEntity.ok().body(listaPessoa);
	}

	@GetMapping("/pessoa/{id}")
	@Operation(summary = "Retorna uma pessoa por id")
	public ResponseEntity<Pessoa> getPessoaById(@PathParam("id") int id) {
		if (id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Pessoa Pessoa = new Pessoa();
		return ResponseEntity.ok().body(Pessoa);
	}

	@PostMapping("/pessoa")
	@Operation(summary = "Adiciona uma pessoa")
	public ResponseEntity<Void> postPessoa(@RequestBody Pessoa pessoa) {
		if (pessoa == null) {
			return ResponseEntity.badRequest().build();
		}
		Pessoa novaPessoa = pessoa;

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/pessoa")
	@Operation(summary = "Atualiza uma pessoa")
	public ResponseEntity<Void> updatePessoa(@RequestBody Pessoa pessoa) {
		if (pessoa == null) {
			return ResponseEntity.badRequest().build();
		}
		Pessoa novaPessoa = pessoa;

		return ResponseEntity.noContent().build();
	}
}
