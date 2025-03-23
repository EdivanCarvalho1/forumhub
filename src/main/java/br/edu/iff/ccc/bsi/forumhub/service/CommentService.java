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

	public Optional<List<Comment>> findAll() {

		return Optional.ofNullable(commentRepository.findAll());

	}

	public Optional<Comment> findOne(Long id) {

		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("O comentário não existe"));

		return Optional.ofNullable(comment);

	}

	public void postComment(Comment comment) {

		commentRepository.save(comment);

	}

	public void deleteComment(Long id) {
		commentRepository.deleteById(id);
	}

	public void updateComment(Long id, Comment updatedComment) {

		Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
		
		existingComment.setContent(updatedComment.getContent());
		existingComment.setCreationDate(updatedComment.getCreationDate());
		existingComment.setDislikes(updatedComment.getDislikes());
		existingComment.setEditDate(updatedComment.getEditDate());
		existingComment.setLikes(updatedComment.getLikes());
		existingComment.setPerson(updatedComment.getPerson());
		existingComment.setPost(updatedComment.getPost());
		
		commentRepository.save(existingComment);
	}

	public Optional<Comment> findByContent(String content) {

		Comment comment = commentRepository.findByContent(content)
				.orElseThrow(() -> new RuntimeException("Comentário não encontrado!"));

		return Optional.ofNullable(comment);
	}
}
