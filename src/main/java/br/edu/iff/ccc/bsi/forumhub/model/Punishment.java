package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.Duration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Punishment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_punishment", nullable = false)
	@NotNull
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private String description;
	
	@Column(name = "punishment_period", nullable = false)
	@NotNull
	private Duration periodo;
}
