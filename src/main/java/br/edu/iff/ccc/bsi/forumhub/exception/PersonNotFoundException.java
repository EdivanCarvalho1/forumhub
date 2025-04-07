package br.edu.iff.ccc.bsi.forumhub.exception;

public class PersonNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(Long id) {
		super("Person not found with ID " + id);
	}

	public PersonNotFoundException(String message) {
		super(message);
	}
	
}
