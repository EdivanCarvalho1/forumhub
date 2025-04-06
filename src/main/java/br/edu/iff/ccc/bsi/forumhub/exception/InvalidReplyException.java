package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidReplyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidReplyException(String message) {
		super(message);
	}

	public InvalidReplyException() {
		super("Invalid reply.");
	}

	public InvalidReplyException(Long id) {
		super("Invalid reply with id " + id + ".");
	}

}
