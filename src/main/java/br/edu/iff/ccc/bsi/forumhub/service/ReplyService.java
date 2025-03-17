package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Reply;
import br.edu.iff.ccc.bsi.forumhub.repository.ReplyRepository;

@Service
public class ReplyService {
	
	@Autowired
	ReplyRepository replyRepository;
	
	public Optional<List<Reply>> findAll(){
		
		return Optional.ofNullable(replyRepository.findAll());
		
	}
	
	public Optional<Reply> findOne(Long id){
		
		Reply Reply = replyRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
		
		return Optional.ofNullable(Reply);
		
	}
	
	public void postReply(Reply Reply) {
		
		replyRepository.save(Reply);
		
	}
	
	public void deleteReply(Long id) {
		replyRepository.deleteById(id);
	}
	
	public void updateReply(Long id) {
		
		Reply updatedReply = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(Reply -> Reply.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		replyRepository.save(updatedReply);
	}
}
