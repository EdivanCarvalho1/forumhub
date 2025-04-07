package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Interaction entity")
public abstract class Interaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false)
	@NotNull
	@PositiveOrZero
	@Schema(description = "Number of likes", example = "0")
	private Integer likes;
	
	@Column(nullable = false)
	@NotNull
	@PositiveOrZero
	@Schema(description = "Number of dislikes", example = "0")
	private Integer dislikes;
	
	@Column(name = "comment_content", nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Comment content", example = "This is a comment")
	private String content;
	
	@Column(name = "creation_date", nullable = false)
	@NotNull
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Schema(description = "Creation date", example = "2021-10-01T15:00:00")
	private LocalDateTime creationDate;
	
	@Column(name = "edit_date")
	@FutureOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Schema(description = "Edit date", example = "2021-10-01T15:00:00")
	private LocalDateTime editDate;
	
	@ManyToOne
	@JoinColumn(name = "id_person", nullable = false)
	@NotNull
	@Schema(description = "Person ID", example = "1")
	private Person person;
	
}
