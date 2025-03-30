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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post_comments")
public class Comment extends Interaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_comment", nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_post", nullable = false)
	@NotNull
	private Post post;
	
	public Comment(Long id, Post post, Integer likes, Integer dislikes, String content, LocalDateTime creationDate, LocalDateTime editDate,
			Person user) {
		super(likes, dislikes, content, creationDate, editDate, user);
		this.id = id;
		this.post = post;
	}
}
