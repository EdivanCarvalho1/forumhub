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
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_post", nullable = false)
	@NotNull
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private Integer likes;
	
	@Column(nullable = false)
	@NotNull
	private Integer dislikes;
	
	@Column(name = "post_content", nullable = false)
	@NotNull
	private String content;
	
	@Column(name = "creation_date", nullable = false)
	@NotNull
	private LocalDateTime creationDate;
	
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@NotNull
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "id_topic", nullable = false)
	@NotNull
	private Topic topic;
}
