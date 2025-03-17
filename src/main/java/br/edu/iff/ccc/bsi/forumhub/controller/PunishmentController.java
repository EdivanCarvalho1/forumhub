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

import br.edu.iff.ccc.bsi.forumhub.model.Punishment;
import br.edu.iff.ccc.bsi.forumhub.service.PunishmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Person", description= "Operações relacionadas a punições")
public class PunishmentController {
	
	@Autowired
	PunishmentService punishmentService;
	
	@GetMapping("/punishment")
	@Operation(summary= "Retorna todos os tipos de punições")
	public ResponseEntity<List<Punishment>> getPunishments(){
		
		List<Punishment> punishmentList = punishmentService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(punishmentList);
		
	}
	
	@GetMapping("/punishment/{id}")
	@Operation(summary= "Retorna um tipo punição pelo ID")
	public ResponseEntity<Punishment> getPunishment(@PathParam(value="id") Long id){
		
		Punishment punishment = punishmentService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(punishment);
	}
	@PostMapping("/punishment")
	@Operation(summary= "Cria um tipo punição")
	public ResponseEntity<Void> postPunishment(@RequestBody Punishment punishment){
		
		if(punishment != null) {
			punishmentService.postPunishment(punishment);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/punishment")
	@Operation(summary= "Deleta um tipo de punição pelo ID")
	public ResponseEntity<Void> deletePunishment(@RequestBody Long id){
		
		if(id != null) {
			punishmentService.deletePunishment(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/punishment")
	@Operation(summary= "Atualiza um tipo de punição pelo ID")
	public ResponseEntity<Void> updatePunishment(@RequestBody Long id){
		
		if(id != null) {
			punishmentService.updatePunishment(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
