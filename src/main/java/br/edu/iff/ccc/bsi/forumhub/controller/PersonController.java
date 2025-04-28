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

import br.edu.iff.ccc.bsi.forumhub.assembler.PersonModel;
import br.edu.iff.ccc.bsi.forumhub.exception.EmptyListException;
import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Person;
import br.edu.iff.ccc.bsi.forumhub.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Person", description = "Operações relacionadas a pessoas")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonModel assembler;

	@GetMapping("/person")
	@Operation(summary = "Retorna todas as pessoas/users")
	public ResponseEntity<CollectionModel<EntityModel<Person>>> getPersons() {

		List<EntityModel<Person>> personList = personService.findAll()
				.orElseThrow(() -> new NotFoundException("Nenhum usuário cadastrado")).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		if (personList.isEmpty()) {
			throw new EmptyListException("Nenhum usuário cadastrado");
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(personList));
	}

	@GetMapping("/person/{id}")
	@Operation(summary = "Retorna uma pessoa pelo ID")
	public ResponseEntity<EntityModel<Person>> getPerson(@Valid @PathParam(value = "id") Long id) {

		Person person = personService.findOne(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado!"));

		EntityModel<Person> personModel = assembler.toModel(person);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personModel);
	}

	@PostMapping("/person")
	@Operation(summary = "Cria um usuário")
	public ResponseEntity<Void> postPerson(@Valid @RequestBody Person person) {

		personService.postPerson(person);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@DeleteMapping("/person/{id}")
	@Operation(summary = "Deleta uma pessoa pelo ID")
	public ResponseEntity<Void> deletePerson(@Valid @PathParam(value = "id") Long id) {

		personService.deletePerson(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	@PutMapping("/person/{id}")
	@Operation(summary = "Atualiza uma pessoa pelo ID")
	public ResponseEntity<Void> updatePerson(@Valid @PathParam(value = "id") Long id, @RequestBody Person person) {

		personService.updatePerson(id, person);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}
