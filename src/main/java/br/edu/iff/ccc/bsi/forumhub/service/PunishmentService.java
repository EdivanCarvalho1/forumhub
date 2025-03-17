package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Punishment;
import br.edu.iff.ccc.bsi.forumhub.repository.PunishmentRepository;

@Service
public class PunishmentService {
	
	@Autowired
	PunishmentRepository punishmentRepository;
	
	public Optional<List<Punishment>> findAll(){
		
		return Optional.ofNullable(punishmentRepository.findAll());
		
	}
	
	public Optional<Punishment> findOne(Long id){
		
		Punishment punishment = punishmentRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
		
		return Optional.ofNullable(punishment);
		
	}
	
	public void postPunishment(Punishment punishment) {
		
		punishmentRepository.save(punishment);
		
	}
	
	public void deletePunishment(Long id) {
		punishmentRepository.deleteById(id);
	}
	
	public void updatePunishment(Long id) {
		
		Punishment updatedPunishment = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(punishment -> punishment.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		punishmentRepository.save(updatedPunishment);
	}
}
