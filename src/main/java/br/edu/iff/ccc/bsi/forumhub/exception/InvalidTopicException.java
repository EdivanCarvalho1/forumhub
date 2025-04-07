package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidTopicException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTopicException(String message) {
		super(message);
	}
	
	public InvalidTopicException() {
		super("Invalid topic.");
	}
	
	public InvalidTopicException(Long id) {
		super("Invalid topic with id " + id + ".");
	}

}
