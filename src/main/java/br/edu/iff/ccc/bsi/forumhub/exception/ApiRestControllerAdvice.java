package br.edu.iff.ccc.bsi.forumhub.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiRestControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	ProblemDetail handleDefaultException(Exception e) {
		
		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Error");
		return body; 
	}
	@ExceptionHandler(CategoryNotFoundException.class)
	ProblemDetail handleCategoryNotFoundException(CategoryNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Category not found error");
		return body;
	}
	@ExceptionHandler(InvalidCategoryException.class)
	ProblemDetail handleInvalidCategoryException(InvalidCategoryException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid category error");
		return body;
	}
	@ExceptionHandler(EmptyListException.class)
	ProblemDetail handleEmptyListException(EmptyListException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Empty list error");
		return body;
		
	}
	@ExceptionHandler(CommentNotFoundException.class)
	ProblemDetail handleCommentNotFoundException(CommentNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Comment not found error");
		return body;
	}
	@ExceptionHandler(InvalidCommentException.class)
	ProblemDetail handleInvalidCommentException(InvalidCommentException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid comment error");
		return body;
	}
}
