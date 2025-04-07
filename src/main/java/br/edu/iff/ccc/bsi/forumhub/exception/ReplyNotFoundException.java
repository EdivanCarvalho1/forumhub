package br.edu.iff.ccc.bsi.forumhub.exception;

public class ReplyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReplyNotFoundException(String message) {
		super(message);
	}
	
	public ReplyNotFoundException() {
		super("Reply not found.");
	}

}
