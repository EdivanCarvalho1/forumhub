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

import br.edu.iff.ccc.bsi.forumhub.model.Punicao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "Punicao Controller", description = "CRUD em punições")
public class PunicaoController {
	@Operation(summary = "Retorna todas as punições")
	@GetMapping("/punicao")
	public ResponseEntity<List<Punicao>> getPunicao(){
		List<Punicao> listaPunicao = new ArrayList<>();
		
		return ResponseEntity.ok().body(listaPunicao);
	}
	@GetMapping("/punicao/{id}")
	@Operation(summary = "Retorna uma punição por id")
	public ResponseEntity<Punicao> getPunicaoById(@PathParam("id") int id){
		if(id < 0) {
			return ResponseEntity.badRequest().build();
		}
		Punicao punicao = new Punicao();
		return ResponseEntity.ok().body(punicao);
	}
	
	@PostMapping("/punicao")
	@Operation(summary = "Adiciona uma punição")
	public ResponseEntity<Void> postPunicao(@RequestBody Punicao punicao){
		if(punicao == null) {
			return ResponseEntity.badRequest().build();
		}
		Punicao novaPunicao = punicao;
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	@PutMapping("/punicao")
	@Operation(summary = "Atualiza uma punição")
	public ResponseEntity<Void> updatePunicao(@RequestBody Punicao punicao){
		if(punicao == null) {
			return ResponseEntity.badRequest().build();
		}
			Punicao novaPunicao = punicao;
			return ResponseEntity.noContent().build();
	}
}
