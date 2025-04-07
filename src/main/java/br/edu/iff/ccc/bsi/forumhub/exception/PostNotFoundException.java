package br.edu.iff.ccc.bsi.forumhub.exception;

public class PostNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
		super(message);
	}

	public PostNotFoundException() {
		super("Post not found.");
	}

}
