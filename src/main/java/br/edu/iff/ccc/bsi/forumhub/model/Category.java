package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_category", nullable = false)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String name;
	
	@Column(name = "creation_date", nullable = false)
	@NotNull(message = "Creation date cannot be null")
	@PastOrPresent(message = "Creation date must be in the past or in the present")
	private LocalDateTime creationDate;
	
	@NotNull(message = "Description cannot be null")
	@NotEmpty(message = "Description cannot be null or empty")
	private String description;
}
