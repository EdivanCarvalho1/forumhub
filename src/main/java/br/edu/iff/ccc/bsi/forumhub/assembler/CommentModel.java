package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.CommentController;
import br.edu.iff.ccc.bsi.forumhub.model.Comment;


@Component
public class CommentModel implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {

	@Override
	public EntityModel<Comment> toModel(Comment comment) {
		return EntityModel.of(comment,
				linkTo(methodOn(CommentController.class).getComment(comment.getId())).withSelfRel(),
				linkTo(methodOn(CommentController.class).getComments()).withRel("comments"));
	}
	
	
}
