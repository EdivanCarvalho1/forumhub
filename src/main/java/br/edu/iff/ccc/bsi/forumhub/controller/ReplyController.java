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

import br.edu.iff.ccc.bsi.forumhub.assembler.ReplyModel;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidReplyException;
import br.edu.iff.ccc.bsi.forumhub.exception.ReplyNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Reply;
import br.edu.iff.ccc.bsi.forumhub.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Reply", description= "Operações relacionadas a respostas de comentários")
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	@Autowired
	private ReplyModel assembler;
	
	@GetMapping("/reply")
	@Operation(summary= "Retorna todas respostas de um comentário")
	public ResponseEntity<CollectionModel<EntityModel<Reply>>> getReplies(){
		
		List<EntityModel<Reply>> replyList = replyService.findAll()
				.orElseThrow(() -> new ReplyNotFoundException("Nenhum usuário cadastrado"))
				.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(replyList));
		
	}
	
	@GetMapping("/reply/{id}")
	@Operation(summary= "Retorna uma resposta de comentário")
	public ResponseEntity<EntityModel<Reply>> getReply(@PathVariable Long id){
		
		Reply reply = replyService.findOne(id).orElseThrow(() -> new ReplyNotFoundException("Usuário não encontrado!"));
		
		EntityModel<Reply> replyModel = assembler.toModel(reply);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(replyModel);
	}
	@PostMapping("/reply")
	@Operation(summary= "Cria uma resposta de comentário")
	public ResponseEntity<Void> postReply(@RequestBody Reply reply){
		
		if(reply != null) {
			replyService.postReply(reply);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidReplyException("Resposta inválida");
	}
	
	@DeleteMapping("/reply/{id}")
	@Operation(summary= "Deleta uma resposta de comentário")
	public ResponseEntity<Void> deleteReply(@PathVariable Long id){
		
		if(id != null) {
			replyService.deleteReply(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidReplyException("Resposta não encontrada!");
	}
	
	@PutMapping("/reply/{id}")
	@Operation(summary= "Atualiza uma resposta de comentário")
	public ResponseEntity<Void> updateReply(@PathVariable Long id, @RequestBody Reply updatedReply){
		
		if(id != null && updatedReply != null) {
			replyService.updateReply(id, updatedReply);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidReplyException("Resposta inválida");
	}
}
