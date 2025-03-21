package br.edu.iff.ccc.bsi.forumhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.forumhub.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public Optional<Comment> findByContent(String content);
}
