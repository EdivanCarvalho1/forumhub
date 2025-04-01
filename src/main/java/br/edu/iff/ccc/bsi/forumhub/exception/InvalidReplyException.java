package br.edu.iff.ccc.bsi.forumhub.exception;

public abstract class InvalidReplyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidReplyException(String message) {
		super(message);
	}

}
