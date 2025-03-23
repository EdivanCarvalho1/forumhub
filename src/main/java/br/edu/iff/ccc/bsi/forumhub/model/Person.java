package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name = "person")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_person", nullable = false)
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String nickname;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String email;
	
	@Column(name="person_password", nullable = false)
	@NotNull
	@NotEmpty
	private String password;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	private String phone;
	
	@Column(name= "signin_date", nullable = false)
	@NotNull
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime signInDate;
	
	@Column(name="status", nullable = false)
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private STATUS status;
	
	@Column(name="person_points", nullable = false)
	@NotNull
	@PositiveOrZero
	private Integer points;
	
}
