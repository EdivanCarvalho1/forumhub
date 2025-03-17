package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired
	TopicRepository topicRepository;
	
	public Optional<List<Topic>> findAll(){
		
		return Optional.ofNullable(topicRepository.findAll());
		
	}
	
	public Optional<Topic> findOne(Long id){
		
		Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("O comentário não existe"));
		
		return Optional.ofNullable(topic);
		
	}
	
	public void postTopic(Topic topic) {
		
		topicRepository.save(topic);
		
	}
	
	public void deleteTopic(Long id) {
		topicRepository.deleteById(id);
	}
	
	public void updateTopic(Long id) {
		
		Topic updatedTopic = findAll()
				.orElseThrow(() -> new RuntimeException("Usuários não existentes"))
				.stream()
				.filter(topic -> topic.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
		
		topicRepository.save(updatedTopic);
	}
}
