package br.edu.iff.ccc.bsi.forumhub.model;

import java.io.Serializable;
import java.time.LocalDate;

import br.edu.iff.ccc.bsi.forumhub.enums.ROLE;
import br.edu.iff.ccc.bsi.forumhub.enums.STATUS;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Admin extends Person implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Admin(Long id, String nickname, String password, String phone, LocalDate signInDate, ROLE role,
			STATUS status, Integer points) {
		super(id, nickname, password, phone, signInDate, role, status, points);
	}
}
