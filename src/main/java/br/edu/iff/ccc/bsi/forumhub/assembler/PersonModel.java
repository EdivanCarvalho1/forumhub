package br.edu.iff.ccc.bsi.forumhub.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.PersonController;
import br.edu.iff.ccc.bsi.forumhub.model.Person;

@Component
public class PersonModel implements RepresentationModelAssembler<Person, EntityModel<Person>> {

	@Override
	public EntityModel<Person> toModel(Person person) {
		return EntityModel.of(person, linkTo(methodOn(PersonController.class).getPerson(person.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).getPersons()).withRel("persons"));
	}

}
