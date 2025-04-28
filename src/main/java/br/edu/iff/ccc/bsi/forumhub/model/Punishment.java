package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.Duration;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "punishment")
@Schema(description = "Punishment entity")
public class Punishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_punishment", nullable = false)
	@Schema(description = "Punishment ID", example = "1")
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Punishment name", example = "Ban")
	private String description;
	
	@Column(name = "punishment_period", nullable = false)
	@NotNull
	@Future
	@Schema(description = "Punishment period", example = "PT1H")
	private String periodo;
}
