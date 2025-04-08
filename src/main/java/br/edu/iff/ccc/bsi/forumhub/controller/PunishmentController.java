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

import br.edu.iff.ccc.bsi.forumhub.assembler.PunishmentModel;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidPunishmentException;
import br.edu.iff.ccc.bsi.forumhub.exception.PunishmentNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Punishment;
import br.edu.iff.ccc.bsi.forumhub.service.PunishmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Punishment", description= "Operações relacionadas a punições")
public class PunishmentController {
	
	@Autowired
	private PunishmentService punishmentService;
	
	@Autowired
	private PunishmentModel assembler;
	
	@GetMapping("/punishment")
	@Operation(summary= "Retorna todos os tipos de punições")
	public ResponseEntity<CollectionModel<EntityModel<Punishment>>> getPunishments(){
		
		List<EntityModel<Punishment>> punishmentList = punishmentService.findAll()
				.orElseThrow(() -> new PunishmentNotFoundException("Nenhuma punição cadastrada"))
				.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(punishmentList));
		
	}
	
	@GetMapping("/punishment/{id}")
	@Operation(summary= "Retorna um tipo punição pelo ID")
	public ResponseEntity<EntityModel<Punishment>> getPunishment(@PathVariable Long id){
		
		Punishment punishment = punishmentService.findOne(id).orElseThrow(() -> new PunishmentNotFoundException("Punição não encontrada!"));
		
		EntityModel<Punishment> punishmentModel = assembler.toModel(punishment);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(punishmentModel);
		
	}
	@PostMapping("/punishment")
	@Operation(summary= "Cria um tipo punição")
	public ResponseEntity<Void> postPunishment(@RequestBody Punishment punishment){
		
		if(punishment != null) {
			punishmentService.postPunishment(punishment);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidPunishmentException("Punição inválida");
	}
	
	@DeleteMapping("/punishment/{id}")
	@Operation(summary= "Deleta um tipo de punição pelo ID")
	public ResponseEntity<Void> deletePunishment(@PathVariable Long id){
		
		if(id != null) {
			punishmentService.deletePunishment(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidPunishmentException("ID inválido");
	}
	
	@PutMapping("/punishment/{id}")
	@Operation(summary= "Atualiza um tipo de punição pelo ID")
	public ResponseEntity<Void> updatePunishment(@PathVariable Long id, @RequestBody Punishment punishment){
		
		if(id != null && punishment != null) {
			punishmentService.updatePunishment(id, punishment);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidPunishmentException("ID inválido");
	}
}
