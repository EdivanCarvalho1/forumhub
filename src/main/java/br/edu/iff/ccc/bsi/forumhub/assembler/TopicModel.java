package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.TopicController;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;

@Component
public class TopicModel implements RepresentationModelAssembler<Topic, EntityModel<Topic>> {

	@Override
    public EntityModel<Topic> toModel(Topic topic) {
        return EntityModel.of(topic,
                linkTo(methodOn(TopicController.class).getTopic(topic.getId())).withSelfRel(),
                linkTo(methodOn(TopicController.class).getTopics()).withRel("topics"),
        		linkTo(methodOn(TopicController.class).postTopic(topic)).withRel("post"),
        		linkTo(methodOn(TopicController.class).updateTopic(topic.getId(), topic)).withRel("update"),
        		linkTo(methodOn(TopicController.class).deleteTopic(topic.getId())).withRel("delete"));

    }
}
