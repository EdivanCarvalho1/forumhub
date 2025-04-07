package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.RoleController;
import br.edu.iff.ccc.bsi.forumhub.model.Role;

@Component
public class RoleModel implements RepresentationModelAssembler<Role, EntityModel<Role>> {

	@Override
    public EntityModel<Role> toModel(Role role) {
        return EntityModel.of(role,
                linkTo(methodOn(RoleController.class).getRole(role.getId())).withSelfRel(),
                linkTo(methodOn(RoleController.class).getRoles()).withRel("roles"),
        		linkTo(methodOn(RoleController.class).postRole(role)).withRel("post"),
        		linkTo(methodOn(RoleController.class).updateRole(role.getId(), role)).withRel("update"),
        		linkTo(methodOn(RoleController.class).deleteRole(role.getId())).withRel("delete"));

    }

}
