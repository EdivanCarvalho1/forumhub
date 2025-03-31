package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Person entity")
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_person", nullable = false)
	@Schema(description = "Person ID", example = "1")
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Person name", example = "John Doe")
	private String nickname;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Person email", example="johndoe@email.com")
	private String email;
	
	@Column(name="person_password", nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Person password", example = "123456")
	private String password;
	
	@Column(nullable = false)
	@NotNull
	@NotEmpty
	@Schema(description = "Person phone", example = "999999999")
	private String phone;
	
	@Column(name= "signin_date", nullable = false)
	@NotNull
	@PastOrPresent
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Schema(description = "Sign in date", example = "2021-10-01T15:00:00")
	private LocalDateTime signInDate;
	
	@Column(name="status", nullable = false)
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	@Schema(description = "Person status", example = "ACTIVE")
	private STATUS status;
	
	@Column(name="person_points", nullable = false)
	@NotNull
	@PositiveOrZero
	@Schema(description = "Person points", example = "0")
	private Integer points;
	
}
