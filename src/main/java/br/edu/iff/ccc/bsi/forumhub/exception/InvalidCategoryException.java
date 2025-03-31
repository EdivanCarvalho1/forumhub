package br.edu.iff.ccc.bsi.forumhub.exception;

public class InvalidCategoryException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidCategoryException() {
		super("Invalid category.");
	}
}
