package br.edu.iff.ccc.bsi.forumhub.exception;

public class CommentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommentNotFoundException() {
		super("Comment not found.");
	}

	public CommentNotFoundException(Long id) {
		super("Comment with id " + id + " not found.");
	}

	public CommentNotFoundException(String message) {
		super(message);
	}

}
