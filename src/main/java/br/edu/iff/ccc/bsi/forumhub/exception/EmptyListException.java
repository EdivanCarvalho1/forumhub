package br.edu.iff.ccc.bsi.forumhub.exception;

public class EmptyListException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmptyListException() {
		super("The list is empty.");
	}
}
