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
	
	public Optional<List<PunishmentLog>> findAll(){
		
		return Optional.ofNullable(punishmentLogRepository.findAll());
		
	}
	
	public Optional<PunishmentLog> findOne(Long id){
		
		PunishmentLog punishmentLog = punishmentLogRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
		
		return Optional.ofNullable(punishmentLog);
		
	}
	
	public void postPunishmentLog(PunishmentLog punishmentLog) {
		
		punishmentLogRepository.save(punishmentLog);
		
	}
	
	public void deletePunishmentLog(Long id) {
		punishmentLogRepository.deleteById(id);
	}
	
	public void updatePunishmentLog(Long id) {
		
		PunishmentLog updatedPunishmentLog = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(punishmentLog -> punishmentLog.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		punishmentLogRepository.save(updatedPunishmentLog);
	}
}
