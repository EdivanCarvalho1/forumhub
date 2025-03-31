package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reply")
@Schema(description = "Reply entity")
public class Reply extends Interaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_reply", nullable = false)
	@Schema(description = "Reply ID", example = "1")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_comment", nullable = false)
	@NotNull
	@Schema(description = "Comment ID", example = "1")
	private Comment comment;
	
	public Reply(Long id, Comment comment, Integer likes, Integer dislikes, String content, LocalDateTime creationDate, LocalDateTime editDate,
			Person person) {
		super(likes, dislikes, content, creationDate, editDate, person);
		
		this.id = id;
	}
	
}
