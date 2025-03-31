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

import br.edu.iff.ccc.bsi.forumhub.exception.CommentNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.exception.EmptyListException;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidCommentException;
import br.edu.iff.ccc.bsi.forumhub.model.Comment;
import br.edu.iff.ccc.bsi.forumhub.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Comment", description= "Operações relacionadas a comentários")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("/comment")
	@Operation(summary= "Retorna todos os comentários")
	public ResponseEntity<List<Comment>> getComments(){
		
		List<Comment> commentList = commentService.findAll().orElseThrow(() -> new CommentNotFoundException());
		
		if (commentList.isEmpty()) {
            throw new EmptyListException();
          }
		
		return ResponseEntity.ok().body(commentList);
		
	}
	
	@GetMapping("/comment/{id}")
	@Operation(summary= "Retorna um comentário pelo id")
	public ResponseEntity<Comment> getComment(@PathParam(value="id") Long id){
		
		Comment comment = commentService.findOne(id).orElseThrow(() -> new CommentNotFoundException(id));
		
		return ResponseEntity.ok().body(comment);
	}
	@PostMapping("/comment")
	@Operation(summary= "Cria um comentário pelo ID")
	public ResponseEntity<Void> postComment(@RequestBody Comment comment){
		
		if(comment != null) {
			commentService.postComment(comment);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidCommentException();
	}
	
	@DeleteMapping("/comment/{id}")
	@Operation(summary= "Deleta um comentário pelo ID")
	public ResponseEntity<Void> deleteComment(@PathParam(value = "id") Long id){
		
		if(id != null) {
			commentService.deleteComment(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidCommentException();
	}
	
	@PutMapping("/comment/{id}")
	@Operation(summary= "Atualiza um comentário pelo ID")
	public ResponseEntity<Void> updateComment(@PathParam(value = "id") Long id, @RequestBody Comment comment){
		
		if(id != null) {
			commentService.updateComment(id, comment);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidCommentException();
	}
}
