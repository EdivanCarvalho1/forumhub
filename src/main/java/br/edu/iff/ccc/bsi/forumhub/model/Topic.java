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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
@Schema(description = "Topic entity")
public class Topic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_topic", nullable = false)
	@Schema(description = "Topic ID", example = "1")
	private Long id;

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Topic title", example = "This is a topic title")
	private String title;

	@Column(name = "creation_date", nullable = false)
	@NotNull
	@PastOrPresent
	@Schema(description = "Creation date", example = "2021-10-01T15:00:00")
	private LocalDateTime creationDate;

	@ManyToOne
	@JoinColumn(name = "id_category", nullable = false)
	@NotNull
	@Schema(description = "Category ID", example = "1")
	private Category category;
	
	
}
