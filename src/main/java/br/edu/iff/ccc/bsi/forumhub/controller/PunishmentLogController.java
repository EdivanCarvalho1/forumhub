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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.assembler.PunishmentLogModel;
import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.PunishmentLog;
import br.edu.iff.ccc.bsi.forumhub.service.PunishmentLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Punishment Log", description = "Operações relacionadas a log de punições")
public class PunishmentLogController {
	@Autowired
	private PunishmentLogService punishmentLogService;

	@Autowired
	private PunishmentLogModel assembler;

	@GetMapping("/punishmentLog")
	@Operation(summary = "Retorna o log de punições")
	public ResponseEntity<CollectionModel<EntityModel<PunishmentLog>>> getPunishmentLogs() {

		List<EntityModel<PunishmentLog>> punishmentLogList = punishmentLogService.findAll()
				.orElseThrow(() -> new NotFoundException("Nenhum log de punição cadastrado")).stream()
				.map(assembler::toModel).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(punishmentLogList));

	}

	@GetMapping("/punishmentLog/{id}")
	@Operation(summary = "Retorna um log de punição específico")
	public ResponseEntity<EntityModel<PunishmentLog>> getPunishmentLog(@PathParam(value = "id") Long id) {

		PunishmentLog punishmentLog = punishmentLogService.findOne(id)
				.orElseThrow(() -> new NotFoundException("Log não encontrado!"));

		EntityModel<PunishmentLog> punishmentLogModel = assembler.toModel(punishmentLog);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(punishmentLogModel);
	}

	@PostMapping("/punishmentLog")
	@Operation(summary = "Aplica uma punição")
	public ResponseEntity<Void> postPunishmentLog(@Valid @RequestBody PunishmentLog punishmentLog) {

		punishmentLogService.postPunishmentLog(punishmentLog);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@DeleteMapping("/punishmentLog/{id}")
	@Operation(summary = "Deleta uma punição pelo ID")
	public ResponseEntity<Void> deletePunishmentLog(@Valid @PathParam(value = "id") Long id) {
		punishmentLogService.deletePunishmentLog(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@PutMapping("/punishmentLog/{id}")
	@Operation(summary = "Atualiza uma punição pelo ID")
	public ResponseEntity<Void> updatePunishmentLog(@Valid @PathParam(value = "id") Long id,
			@RequestBody PunishmentLog punishmentLog) {
		punishmentLogService.updatePunishmentLog(id, punishmentLog);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
