package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.ReplyController;
import br.edu.iff.ccc.bsi.forumhub.model.Reply;

@Component
public class ReplyModel implements RepresentationModelAssembler<Reply, EntityModel<Reply>> {

	@Override
    public EntityModel<Reply> toModel(Reply reply) {
        return EntityModel.of(reply,
                linkTo(methodOn(ReplyController.class).getReply(reply.getId())).withSelfRel(),
                linkTo(methodOn(ReplyController.class).getReplies()).withRel("replies"),
        		linkTo(methodOn(ReplyController.class).postReply(reply)).withRel("post"),
        		linkTo(methodOn(ReplyController.class).updateReply(reply.getId(), reply)).withRel("update"),
        		linkTo(methodOn(ReplyController.class).deleteReply(reply.getId())).withRel("delete"));

    }

}
