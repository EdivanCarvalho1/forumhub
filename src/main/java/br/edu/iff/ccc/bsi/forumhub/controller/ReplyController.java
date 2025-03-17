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

import br.edu.iff.ccc.bsi.forumhub.model.Reply;
import br.edu.iff.ccc.bsi.forumhub.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Person", description= "Operações relacionadas a respostas de comentários")
public class ReplyController {
	
	@Autowired
	ReplyService replyService;
	
	@GetMapping("/reply")
	@Operation(summary= "Retorna todas respostas de um comentário")
	public ResponseEntity<List<Reply>> getReplys(){
		
		List<Reply> replyList = replyService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(replyList);
		
	}
	
	@GetMapping("/reply/{id}")
	@Operation(summary= "Retorna uma resposta de comentário")
	public ResponseEntity<Reply> getReply(@PathParam(value="id") Long id){
		
		Reply reply = replyService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(reply);
	}
	@PostMapping("/reply")
	@Operation(summary= "Cria uma resposta de comentário")
	public ResponseEntity<Void> postReply(@RequestBody Reply reply){
		
		if(reply != null) {
			replyService.postReply(reply);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/reply")
	@Operation(summary= "Deleta uma resposta de comentário")
	public ResponseEntity<Void> deleteReply(@RequestBody Long id){
		
		if(id != null) {
			replyService.deleteReply(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/reply")
	@Operation(summary= "Atualiza uma resposta de comentário")
	public ResponseEntity<Void> updateReply(@RequestBody Long id){
		
		if(id != null) {
			replyService.updateReply(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
