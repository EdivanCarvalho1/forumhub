package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidPersonRoleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidPersonRoleException() {
		super("Invalid Person Role");
	}

	public InvalidPersonRoleException(String message) {
		super(message);
	}

	public InvalidPersonRoleException(Long id) {
		super("Invalid Person Role with id " + id + ".");
	}

}
