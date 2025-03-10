package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.iff.ccc.bsi.forumhub.enums.ROLE;
import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "users")
abstract class Person implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_user", nullable = false)
	@NotNull
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private String nickname;
	
	@Column(name="user_password", nullable = false)
	@NotNull
	private String password;
	
	@Column(nullable = false)
	@NotNull
	private String phone;
	
	@Column(name= "signin_date", nullable = false)
	@NotNull
	private LocalDate signInDate;
	
	@Column(name="user_role", nullable = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	private ROLE role;
	
	@Column(name="status", nullable = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	private STATUS status;
	
	@Column(name="user_points", nullable = false)
	@NotNull
	private Integer points;
	
}
