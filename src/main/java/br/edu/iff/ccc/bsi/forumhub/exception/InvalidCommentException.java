package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidCommentException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidCommentException() {
		super("Invalid comment.");
	}

}
