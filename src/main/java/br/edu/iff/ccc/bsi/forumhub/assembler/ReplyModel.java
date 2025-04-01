package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import br.edu.iff.ccc.bsi.forumhub.controller.ReplyController;
import br.edu.iff.ccc.bsi.forumhub.model.Reply;

public class ReplyModel implements RepresentationModelAssembler<Reply, EntityModel<Reply>> {

	@Override
	public EntityModel<Reply> toModel(Reply reply) {
		return EntityModel.of(reply, linkTo(methodOn(ReplyController.class).getReply(reply.getId())).withSelfRel(),
				linkTo(methodOn(ReplyController.class).getReplies()).withRel("replies"));
	}

}
