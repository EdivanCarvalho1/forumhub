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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
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
@Table(name = "punishment_log")
public class PunishmentLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id_punishment_log", nullable = false)
	@NotNull
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="id_person", nullable = false)
	@NotNull
	private Person idPerson;
	
	@ManyToOne
	@JoinColumn(name="id_punishment", nullable = false)
	@NotNull
	private Punishment idPunishment;
	
	@Column(name = "start_date", nullable = false)
	@NotNull
	@PastOrPresent
	private LocalDateTime startDate;
	
	@Column(name = "end_date", nullable = false)
	@NotNull
	@Future
	private LocalDateTime endDate;
}
