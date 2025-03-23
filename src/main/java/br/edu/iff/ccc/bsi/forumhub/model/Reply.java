package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Reply extends Interaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_comment", nullable = false)
	@NotNull
	private Comment comment;
	
	public Reply(Long id, Comment comment, Integer likes, Integer dislikes, String content, LocalDateTime creationDate, LocalDateTime editDate,
			Person person) {
		super(id, likes, dislikes, content, creationDate, editDate, person);
	}
	
}
