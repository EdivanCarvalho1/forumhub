package br.edu.iff.ccc.bsi.forumhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Person", description= "Operações relacionadas a pessoas")
public class PersonController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping("/person")
	@Operation(summary= "Retorna todas as pessoas/users")
	public ResponseEntity<List<Person>> getPersons(){
		
		List<Person> personList = personService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(personList);
		
	}
	
	@GetMapping("/person/{id}")
	@Operation(summary= "Retorna uma pessoa pelo ID")
	public ResponseEntity<Person> getPerson(@PathParam(value="id") Long id){
		
		Person person = personService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(person);
	}
	
	
	@PostMapping("/person")
	@Operation(summary= "Cria um usuário")
	public ResponseEntity<Void> postPerson(@RequestBody Person person){
		
		if(person != null) {
			personService.postPerson(person);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/person/{id}")
	@Operation(summary= "Deleta uma pessoa pelo ID")
	public ResponseEntity<Void> deletePerson(@PathParam(value = "id") Long id){
		
		if(id != null) {
			personService.deletePerson(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/person/{id}")
	@Operation(summary= "Atualiza uma pessoa pelo ID")
	public ResponseEntity<Void> updatePerson(@PathParam(value = "id") Long id, @RequestBody Person person){
		
		if(id != null) {
			personService.updatePerson(id, person);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
