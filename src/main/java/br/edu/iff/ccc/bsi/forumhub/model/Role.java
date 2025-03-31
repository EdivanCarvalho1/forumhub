package br.edu.iff.ccc.bsi.forumhub.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Role entity")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "Role ID", example = "1")
	private Long id;
	
	@Column(name = "role_name", nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Role name", example = "ADMIN")
	private String roleName;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Role description", example = "Administrator")
	private String description;
	
}
