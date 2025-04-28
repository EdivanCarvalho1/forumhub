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

import br.edu.iff.ccc.bsi.forumhub.assembler.ReplyModel;
import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Reply;
import br.edu.iff.ccc.bsi.forumhub.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Reply", description = "Operações relacionadas a respostas de comentários")
public class ReplyController {

	@Autowired
	private ReplyService replyService;

	@Autowired
	private ReplyModel assembler;

	@GetMapping("/reply")
	@Operation(summary = "Retorna todas respostas de um comentário")
	public ResponseEntity<CollectionModel<EntityModel<Reply>>> getReplies() {

		List<EntityModel<Reply>> replyList = replyService.findAll()
				.orElseThrow(() -> new NotFoundException("Nenhum usuário cadastrado")).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(replyList));

	}

	@GetMapping("/reply/{id}")
	@Operation(summary = "Retorna uma resposta de comentário")
	public ResponseEntity<EntityModel<Reply>> getReply(@Valid @PathParam(value = "id") Long id) {

		Reply reply = replyService.findOne(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

		EntityModel<Reply> replyModel = assembler.toModel(reply);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(replyModel);
	}

	@PostMapping("/reply")
	@Operation(summary = "Cria uma resposta de comentário")
	public ResponseEntity<Void> postReply(@Valid @RequestBody Reply reply) {

		replyService.postReply(reply);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@DeleteMapping("/reply/{id}")
	@Operation(summary = "Deleta uma resposta de comentário")
	public ResponseEntity<Void> deleteReply(@Valid @PathParam(value = "id") Long id) {

		replyService.deleteReply(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	@PutMapping("/reply/{id}")
	@Operation(summary = "Atualiza uma resposta de comentário")
	public ResponseEntity<Void> updateReply(@Valid @PathParam(value = "id") Long id, @RequestBody Reply updatedReply) {

		replyService.updateReply(id, updatedReply);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}
