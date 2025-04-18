package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidPunishmentLogException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPunishmentLogException(String message) {
		super(message);
	}

	public InvalidPunishmentLogException() {
		super("Invalid punishment log.");
	}

	public InvalidPunishmentLogException(Long id) {
		super("Invalid punishment log with id " + id + ".");
	}

}
