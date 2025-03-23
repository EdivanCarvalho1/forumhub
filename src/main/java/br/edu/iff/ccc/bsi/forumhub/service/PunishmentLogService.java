package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.PunishmentLog;
import br.edu.iff.ccc.bsi.forumhub.repository.PunishmentLogRepository;

@Service
public class PunishmentLogService {

	@Autowired
	PunishmentLogRepository punishmentLogRepository;

	public Optional<List<PunishmentLog>> findAll() {

		return Optional.ofNullable(punishmentLogRepository.findAll());

	}

	public Optional<PunishmentLog> findOne(Long id) {

		PunishmentLog punishmentLog = punishmentLogRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("O comentário não existe"));

		return Optional.ofNullable(punishmentLog);

	}

	public void postPunishmentLog(PunishmentLog punishmentLog) {

		punishmentLogRepository.save(punishmentLog);

	}

	public void deletePunishmentLog(Long id) {
		punishmentLogRepository.deleteById(id);
	}

	public void updatePunishmentLog(Long id, PunishmentLog updatedLog) {

		PunishmentLog existingLog = punishmentLogRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("PunishmentLog not found"));

		existingLog.setEndDate(updatedLog.getEndDate());
		existingLog.setIdPerson(updatedLog.getIdPerson());
		existingLog.setIdPunishment(updatedLog.getIdPunishment());
		existingLog.setStartDate(updatedLog.getStartDate());

		punishmentLogRepository.save(existingLog);

	}
}
