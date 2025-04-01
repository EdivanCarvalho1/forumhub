package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "category")
@Schema(description = "Category entity")
public class Category implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_category", nullable = false)
	@Schema(description = "Category ID", example = "1")
	private Long id;
	

	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Category name", example = "This is a category name")
	private String name;
	

	@Column(name = "creation_date", nullable = false)
	@NotNull(message = "Creation date cannot be null")
	@PastOrPresent(message = "Creation date must be in the past or in the present")
	@Schema(description = "Creation date", example = "2021-10-01T15:00:00")
	private LocalDateTime creationDate;
	

	@NotNull(message = "Description cannot be null")
	@NotEmpty(message = "Description cannot be null or empty")
	@Schema(description = "Category description", example = "This is a category description")
	private String description;
}
