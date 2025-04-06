package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidPostException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPostException(String message) {
		super(message);
	}

	public InvalidPostException() {
		super("Invalid post.");
	}

	public InvalidPostException(Long id) {
		super("Invalid post with id " + id + ".");
	}

}
