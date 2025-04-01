package br.edu.iff.ccc.bsi.forumhub.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import br.edu.iff.ccc.bsi.forumhub.controller.TopicController;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;

public class TopicModel implements RepresentationModelAssembler<Topic, EntityModel<Topic>> {

	@Override
	public EntityModel<Topic> toModel(Topic topic) {
		return EntityModel.of(topic, linkTo(methodOn(TopicController.class).getTopic(topic.getId())).withSelfRel(),
				linkTo(methodOn(TopicController.class).getTopics()).withRel("topics"));
	}
}
