package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRoleException(String message) {
		super(message);
	}

	public InvalidRoleException() {
		super("Invalid role.");
	}

	public InvalidRoleException(Long id) {
		super("Invalid role with id " + id + ".");
	}

}
