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

import br.edu.iff.ccc.bsi.forumhub.model.Topico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1")
public class TopicoController {

	@GetMapping("/topico")
	@Operation(summary = "Retorna todos os tópicos")
	public ResponseEntity<List<Topico>> getTopico() {
		List<Topico> listaTopico = new ArrayList<>();

		return ResponseEntity.ok().body(listaTopico);
	}

	@GetMapping("/topico/{id}")
	@Operation(summary = "Retorna um tópico por id")
	public ResponseEntity<Topico> getTopicoById(@PathParam("id") int id) {
		if (id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Topico topico = new Topico();
		return ResponseEntity.ok().body(topico);
	}

	@PostMapping("/topico")
	@Operation(summary = "Adiciona um tópico")
	public ResponseEntity<Void> postTopico(@RequestBody Topico topico) {
		if (topico == null) {
			return ResponseEntity.badRequest().build();
		}
		Topico novaTopico = topico;

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/topico")
	@Operation(summary = "Atualiza um tópico")
	public ResponseEntity<Void> updateTopico(@RequestBody Topico topico) {
		if (topico == null) {
			return ResponseEntity.badRequest().build();
		}
		Topico novaTopico = topico;

		return ResponseEntity.noContent().build();
	}
}
