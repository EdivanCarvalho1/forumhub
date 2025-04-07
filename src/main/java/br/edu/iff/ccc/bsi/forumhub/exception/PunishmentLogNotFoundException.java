package br.edu.iff.ccc.bsi.forumhub.exception;

public class PunishmentLogNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PunishmentLogNotFoundException(String message) {
		super(message);
	}
	
	public PunishmentLogNotFoundException() {
		super("Punishment log not found.");
	}

}
