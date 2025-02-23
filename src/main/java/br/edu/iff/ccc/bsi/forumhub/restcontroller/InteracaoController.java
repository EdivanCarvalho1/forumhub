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

import br.edu.iff.ccc.bsi.forumhub.model.Interacao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Interacao Controller", description = "Cruid em Interações")
public class InteracaoController {

	@GetMapping("/interacao")
	@Operation(summary = "Retorna todas as interações")
	public ResponseEntity<List<Interacao>> getinteracao() {
		List<Interacao> listainteracao = new ArrayList<>();

		return ResponseEntity.ok().body(listainteracao);
	}

	@GetMapping("/interacao/{id}")
	@Operation(summary = "Retorna a interação por id")
	public ResponseEntity<Interacao> getinteracaoById(@PathParam("id") int id) {
		if (id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Interacao interacao = new Interacao();
		return ResponseEntity.ok().body(interacao);
	}

	@PostMapping("/interacao")
	@Operation(summary = "Adiciona uma nova interação")
	public ResponseEntity<Void> postInteracao(@RequestBody Interacao interacao) {
		if (interacao == null) {
			return ResponseEntity.badRequest().build();
		}
		Interacao novainteracao = interacao;

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/interacao")
	@Operation(summary = "Atualiza uma interação")
	public ResponseEntity<Void> updateInteracao(@RequestBody Interacao interacao) {
		if (interacao == null) {
			return ResponseEntity.badRequest().build();
		}
		Interacao novainteracao = interacao;

		return ResponseEntity.noContent().build();
	}
}
