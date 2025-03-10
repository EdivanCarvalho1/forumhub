package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class PunishmentLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_punishment_log", nullable = false)
	@NotNull
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_user", nullable = false)
	@NotNull
	private Person idUser;
	
	@ManyToOne
	@JoinColumn(name="id_punishment", nullable = false)
	@NotNull
	private Punishment idPunishment;
	
	@Column(name = "start_date", nullable = false)
	@NotNull
	private LocalDateTime startDate;
	
	@Column(name = "end_date", nullable = false)
	@NotNull
	private LocalDateTime endDate;
}
