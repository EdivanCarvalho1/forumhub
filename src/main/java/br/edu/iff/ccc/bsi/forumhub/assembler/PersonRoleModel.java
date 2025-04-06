package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.PersonRoleController;
import br.edu.iff.ccc.bsi.forumhub.model.PersonRole;

@Component
public class PersonRoleModel implements RepresentationModelAssembler<PersonRole, EntityModel<PersonRole>> {

	@Override
    public EntityModel<PersonRole> toModel(PersonRole personRole) {
        return EntityModel.of(personRole,
                linkTo(methodOn(PersonRoleController.class).getPersonRole(personRole.getId())).withSelfRel(),
                linkTo(methodOn(PersonRoleController.class).getPersonRoles()).withRel("person_roles"),
        		linkTo(methodOn(PersonRoleController.class).postPersonRole(personRole)).withRel("post"),
        		linkTo(methodOn(PersonRoleController.class).updatePersonRole(personRole.getId(), personRole)).withRel("update"),
        		linkTo(methodOn(PersonRoleController.class).deletePersonRole(personRole.getId())).withRel("delete"));

    }
	
}
