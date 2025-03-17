package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Comment;
import br.edu.iff.ccc.bsi.forumhub.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	public Optional<List<Comment>> findAll(){
		
		return Optional.ofNullable(commentRepository.findAll());
		
	}
	
	public Optional<Comment> findOne(Long id){
		
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
		
		return Optional.ofNullable(comment);
		
	}
	
	public void postComment(Comment comment) {
		
		commentRepository.save(comment);
		
	}
	
	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}
	
	public void updateComment(Long id) {
		
		Comment updatedComment = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(comment -> comment.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		commentRepository.save(updatedComment);
	}
}
