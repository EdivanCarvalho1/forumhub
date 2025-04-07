package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidPunishmentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPunishmentException(String message) {
		super(message);
	}

	public InvalidPunishmentException() {
		super("Invalid punishment.");
	}

	public InvalidPunishmentException(Long id) {
		super("Invalid punishment with id " + id + ".");
	}

}
