package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.PostController;
import br.edu.iff.ccc.bsi.forumhub.model.Post;


@Component
public class PostModel implements RepresentationModelAssembler<Post, EntityModel<Post>> {

	@Override
	public EntityModel<Post> toModel(Post post) {
		return EntityModel.of(post, linkTo(methodOn(PostController.class).getPost(post.getId())).withSelfRel(),
				linkTo(methodOn(PostController.class).getPosts()).withRel("posts"));
	}

}
