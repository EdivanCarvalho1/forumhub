package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;

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
@Table(name = "person_roles")
@Schema(description = "Person Role entity")
public class PersonRole implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_person_roles", nullable = false)
	@Schema(description = "Person Role ID", example = "1")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_person", nullable = false)
	@NotNull
	@Schema(description = "Person ID", example = "1")
	private Person Person;
	
	@ManyToOne
	@JoinColumn(name = "id_role", nullable = false)
	@NotNull
	@Schema(description = "Role ID", example = "1")
	private Role Role;
	
	
}
