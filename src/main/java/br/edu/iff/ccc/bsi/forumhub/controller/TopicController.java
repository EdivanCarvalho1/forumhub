package br.edu.iff.ccc.bsi.forumhub.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.assembler.TopicModel;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidTopicException;
import br.edu.iff.ccc.bsi.forumhub.exception.TopicNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Topic;
import br.edu.iff.ccc.bsi.forumhub.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Topic", description= "Operações relacionadas a topicos")
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicModel assembler;
	
	@GetMapping("/topic")
	@Operation(summary= "Retorna todos os tópicos")
	public ResponseEntity<CollectionModel<EntityModel<Topic>>> getTopics(){
		
		List<EntityModel<Topic>> topicList = topicService.findAll()
				.orElseThrow(() -> new TopicNotFoundException("Nenhum tópico cadastrado"))
				.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(topicList));
		
	}
	
	@GetMapping("/topic/{id}")
	@Operation(summary= "Retorna um tópico específico")
	public ResponseEntity<EntityModel<Topic>> getTopic(@PathParam(value="id") Long id){
		
		Topic topic = topicService.findOne(id).orElseThrow(() -> new TopicNotFoundException("Tópico não encontrado!"));
		
		EntityModel<Topic> topicModel = assembler.toModel(topic);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(topicModel);
	}
	@PostMapping("/topic")
	@Operation(summary= "Cria um tópico")
	public ResponseEntity<Void> postTopic(@RequestBody Topic topic){
		
		if(topic != null) {
			topicService.postTopic(topic);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidTopicException("Tópico inválido");
	}
	
	@DeleteMapping("/topic/{id}")
	@Operation(summary= "Deleta uma tópico pelo ID")
	public ResponseEntity<Void> deleteTopic(@PathParam(value = "id") Long id){
		
		if(id != null) {
			topicService.deleteTopic(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new TopicNotFoundException("Tópico não encontrado!");
	}
	
	@PutMapping("/topic/{id}")
	@Operation(summary= "Atualiza um tópico pelo ID")
	public ResponseEntity<Void> updateTopic(@PathParam(value = "id") Long id, @RequestBody Topic topic){
		
		if(id != null && topic != null) {
			topicService.updateTopic(id, topic);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidTopicException("Tópico inválido");
	}
}
