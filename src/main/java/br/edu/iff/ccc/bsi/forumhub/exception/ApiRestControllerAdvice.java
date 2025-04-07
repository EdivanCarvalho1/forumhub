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
	@ExceptionHandler(PersonNotFoundException.class)
	ProblemDetail handlePersonNotFoundException(PersonNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Person not found error");
		return body;
	}
	@ExceptionHandler(InvalidPersonException.class)
	ProblemDetail handleInvalidPersonException(InvalidPersonException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid person error");
		return body;
	}
	@ExceptionHandler(PersonRoleNotFoundException.class)
	ProblemDetail handlePersonRoleNotFoundException(PersonRoleNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Person role not found error");
		return body;
	}
	@ExceptionHandler(InvalidPersonRoleException.class)
	ProblemDetail handleInvalidPersonRoleException(InvalidPersonRoleException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid person role error");
		return body;
	}
	@ExceptionHandler(TopicNotFoundException.class)
	ProblemDetail handleTopicNotFoundException(TopicNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Topic not found error");
		return body;
	}
	@ExceptionHandler(InvalidTopicException.class)
	ProblemDetail handleInvalidTopicException(InvalidTopicException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid topic error");
		return body;
	}
	@ExceptionHandler(InvalidPunishmentException.class)
	ProblemDetail handleInvalidPunishmentException(InvalidPunishmentException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid punishment error");
		return body;
	}
	@ExceptionHandler(PunishmentNotFoundException.class)
	ProblemDetail handlePunishmentNotFoundException(PunishmentNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Punishment not found error");
		return body;
	}
	@ExceptionHandler(PunishmentLogNotFoundException.class)
	ProblemDetail handlePunishmentLogNotFoundException(PunishmentLogNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Punishment log not found error");
		return body;
	}
	@ExceptionHandler(InvalidPunishmentLogException.class)
	ProblemDetail handleInvalidPunishmentLogException(InvalidPunishmentLogException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid punishment log error");
		return body;
	}
	@ExceptionHandler(InvalidReplyException.class)
	ProblemDetail handleInvalidReplyException(InvalidReplyException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid reply error");
		return body;
	}
	@ExceptionHandler(ReplyNotFoundException.class)
	ProblemDetail handleReplyNotFoundException(ReplyNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Reply not found error");
		return body;
	}
	@ExceptionHandler(RoleNotFoundException.class)
	ProblemDetail handleRoleNotFoundException(RoleNotFoundException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Role not found error");
		return body;
	}
	@ExceptionHandler(InvalidRoleException.class)
	ProblemDetail handleInvalidRoleException(InvalidRoleException e) {

		ProblemDetail body = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), e.getMessage());
		body.setTitle("Invalid role error");
		return body;
	}
}
