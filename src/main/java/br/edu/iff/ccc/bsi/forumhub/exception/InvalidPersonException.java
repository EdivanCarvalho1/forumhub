package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidPersonException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPersonException(String message) {
		super(message);
	}

	public InvalidPersonException() {
		super("Invalid person.");
	}

	public InvalidPersonException(Long id) {
		super("Invalid person with id " + id + ".");
	}

}
