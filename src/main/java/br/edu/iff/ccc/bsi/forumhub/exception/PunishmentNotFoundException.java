package br.edu.iff.ccc.bsi.forumhub.exception;

public class PunishmentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PunishmentNotFoundException(String message) {
		super(message);
	}
	
	public PunishmentNotFoundException() {
		super("Punishment not found.");
	}

}
