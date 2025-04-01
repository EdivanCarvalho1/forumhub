package br.edu.iff.ccc.bsi.forumhub.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import br.edu.iff.ccc.bsi.forumhub.controller.PunishmentLogController;
import br.edu.iff.ccc.bsi.forumhub.model.PunishmentLog;

public class PunishmentLogModel implements RepresentationModelAssembler<PunishmentLog, EntityModel<PunishmentLog>> {

	@Override
	public EntityModel<PunishmentLog> toModel(PunishmentLog punishmentLog) {
		return EntityModel.of(punishmentLog,
				linkTo(methodOn(PunishmentLogController.class).getPunishmentLog(punishmentLog.getId())).withSelfRel(),
				linkTo(methodOn(PunishmentLogController.class).getPunishmentLogs()).withRel("punishmentLogs"));
	}
}
