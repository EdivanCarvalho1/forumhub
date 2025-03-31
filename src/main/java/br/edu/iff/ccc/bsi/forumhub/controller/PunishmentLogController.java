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

import br.edu.iff.ccc.bsi.forumhub.model.PunishmentLog;
import br.edu.iff.ccc.bsi.forumhub.service.PunishmentLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Punishment Log", description= "Operações relacionadas a log de punições")
public class PunishmentLogController {
	@Autowired
	PunishmentLogService punishmentLogService;
	
	@GetMapping("/punishmentLog")
	@Operation(summary= "Retorna o log de punições")
	public ResponseEntity<List<PunishmentLog>> getPunishmentLogs(){
		
		List<PunishmentLog> punishmentLogList = punishmentLogService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(punishmentLogList);
		
	}
	
	@GetMapping("/punishmentLog/{id}")
	@Operation(summary= "Retorna um log de punição específico")
	public ResponseEntity<PunishmentLog> getPunishmentLog(@PathParam(value="id") Long id){
		
		PunishmentLog punishmentLog = punishmentLogService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(punishmentLog);
	}
	@PostMapping("/punishmentLog")
	@Operation(summary= "Aplica uma punição")
	public ResponseEntity<Void> postPunishmentLog(@RequestBody PunishmentLog punishmentLog){
		
		if(punishmentLog != null) {
			punishmentLogService.postPunishmentLog(punishmentLog);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/punishmentLog/{id}")
	@Operation(summary= "Deleta uma punição pelo ID")
	public ResponseEntity<Void> deletePunishmentLog(@PathParam(value = "id") Long id){
		
		if(id != null) {
			punishmentLogService.deletePunishmentLog(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/punishmentLog/{id}")
	@Operation(summary= "Atualiza uma punição pelo ID")
	public ResponseEntity<Void> updatePunishmentLog(@PathParam(value = "id") Long id, @RequestBody PunishmentLog punishmentLog){
		
		if(id != null) {
			punishmentLogService.updatePunishmentLog(id, punishmentLog);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
