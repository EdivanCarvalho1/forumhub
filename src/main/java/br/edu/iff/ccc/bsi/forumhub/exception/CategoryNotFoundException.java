package br.edu.iff.ccc.bsi.forumhub.exception;


public class CategoryNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CategoryNotFoundException() {
		super("Category not found.");
	}
	public CategoryNotFoundException(Long id) {
		super("Category with id "+ id +" not found.");
	}
	public CategoryNotFoundException(String message) {
		super(message);
	}

}
