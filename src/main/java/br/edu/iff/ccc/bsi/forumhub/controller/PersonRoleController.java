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

import br.edu.iff.ccc.bsi.forumhub.assembler.PersonRoleModel;
import br.edu.iff.ccc.bsi.forumhub.exception.EmptyListException;
import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.PersonRole;
import br.edu.iff.ccc.bsi.forumhub.service.PersonRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Person Role", description = "Operações relacionadas a roles de pessoas")
public class PersonRoleController {

	@Autowired
	private PersonRoleService personRoleService;

	@Autowired
	private PersonRoleModel assembler;

	@GetMapping("/personrole")
	@Operation(summary = "Retorna todas as roles de pessoas")
	public ResponseEntity<CollectionModel<EntityModel<PersonRole>>> getPersonRoles() {

		List<EntityModel<PersonRole>> personRoleList = personRoleService.findAll()
				.orElseThrow(() -> new NotFoundException("Nenhuma role de usuário cadastrada!")).stream()
				.map(assembler::toModel).collect(Collectors.toList());

		if (personRoleList.isEmpty()) {
			throw new EmptyListException();
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(personRoleList));

	}

	@GetMapping("/personrole/{id}")
	@Operation(summary = "Retorna uma role de pessoa pelo ID")
	public ResponseEntity<EntityModel<PersonRole>> getPersonRole(@Valid @PathParam(value = "id") Long id) {

		PersonRole personrole = personRoleService.findOne(id)
				.orElseThrow(() -> new NotFoundException("Role de usuário não encontrada!"));

		EntityModel<PersonRole> personRoleModel = assembler.toModel(personrole);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personRoleModel);
	}

	@PostMapping("/personrole")
	@Operation(summary = "Cria uma role de pessoa")
	public ResponseEntity<Void> postPersonRole(@Valid @RequestBody PersonRole personRole) {

		personRoleService.postPersonRole(personRole);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@DeleteMapping("/personrole/{id}")
	@Operation(summary = "Deleta uma role de pessoa pelo ID")
	public ResponseEntity<Void> deletePersonRole(@Valid @PathParam(value = "id") Long id) {

		personRoleService.deletePersonRole(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

	@PutMapping("/personrole/{id}")
	@Operation(summary = "Atualiza uma role de pessoa pelo ID")
	public ResponseEntity<Void> updatePersonRole(@Valid @PathParam(value = "id") Long id,
			@RequestBody PersonRole personRole) {

		personRoleService.updatePersonRole(id, personRole);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}
}
