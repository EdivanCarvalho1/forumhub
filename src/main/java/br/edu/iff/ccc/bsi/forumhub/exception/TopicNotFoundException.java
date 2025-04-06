package br.edu.iff.ccc.bsi.forumhub.exception;

public class TopicNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TopicNotFoundException(String message) {
		super(message);
	}
	
	public TopicNotFoundException() {
		super("Topic not found.");
	}
}
