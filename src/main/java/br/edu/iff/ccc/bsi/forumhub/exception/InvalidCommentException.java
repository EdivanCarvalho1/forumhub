package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidCommentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCommentException() {
		super("Invalid comment.");
	}

	public InvalidCommentException(Long id) {
		super("Invalid comment with id " + id + ".");
	}

	public InvalidCommentException(String message) {
		super(message);
	}

}
