package br.edu.iff.ccc.bsi.forumhub.assembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import br.edu.iff.ccc.bsi.forumhub.controller.PunishmentController;
import br.edu.iff.ccc.bsi.forumhub.model.Punishment;

@Component
public class PunishmentModel implements RepresentationModelAssembler<Punishment, EntityModel<Punishment>> {

	@Override
	public EntityModel<Punishment> toModel(Punishment punishment) {
		return EntityModel.of(punishment,
				linkTo(methodOn(PunishmentController.class).getPunishment(punishment.getId())).withSelfRel(),
				linkTo(methodOn(PunishmentController.class).getPunishments()).withRel("punishments"));
	}

}
