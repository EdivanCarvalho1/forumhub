package br.edu.iff.ccc.bsi.forumhub.assembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.PunishmentLogController;
import br.edu.iff.ccc.bsi.forumhub.model.PunishmentLog;

@Component
public class PunishmentLogModel implements RepresentationModelAssembler<PunishmentLog, EntityModel<PunishmentLog>> {

	@Override
    public EntityModel<PunishmentLog> toModel(PunishmentLog punishmentLog) {
        return EntityModel.of(punishmentLog,
                linkTo(methodOn(PunishmentLogController.class).getPunishmentLog(punishmentLog.getId())).withSelfRel(),
                linkTo(methodOn(PunishmentLogController.class).getPunishmentLogs()).withRel("punishmentLog"),
        		linkTo(methodOn(PunishmentLogController.class).postPunishmentLog(punishmentLog)).withRel("post"),
        		linkTo(methodOn(PunishmentLogController.class).updatePunishmentLog(punishmentLog.getId(), punishmentLog)).withRel("update"),
        		linkTo(methodOn(PunishmentLogController.class).deletePunishmentLog(punishmentLog.getId())).withRel("delete"));
    }
}
