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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.forumhub.assembler.PersonRoleModel;
import br.edu.iff.ccc.bsi.forumhub.exception.EmptyListException;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidPersonRoleException;
import br.edu.iff.ccc.bsi.forumhub.exception.PersonRoleNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.PersonRole;
import br.edu.iff.ccc.bsi.forumhub.service.PersonRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Person Role", description= "Operações relacionadas a roles de pessoas")
public class PersonRoleController {
	
	@Autowired
	private PersonRoleService personRoleService;
	
	@Autowired
	private PersonRoleModel assembler;
	
	@GetMapping("/personrole")
	@Operation(summary= "Retorna todas as roles de pessoas")
	public ResponseEntity<CollectionModel<EntityModel<PersonRole>>> getPersonRoles(){
		
		List<EntityModel<PersonRole>> personRoleList = personRoleService.findAll()
				.orElseThrow(() -> new PersonRoleNotFoundException("Nenhuma role de usuário cadastrada!"))
				.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());
		
		if (personRoleList.isEmpty()) {
			throw new EmptyListException();
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(personRoleList));
		
	}
	
	@GetMapping("/personrole/{id}")
	@Operation(summary= "Retorna uma role de pessoa pelo ID")
	public ResponseEntity<EntityModel<PersonRole>> getPersonRole(@PathVariable Long id){
		
		PersonRole personrole = personRoleService.findOne(id).orElseThrow(() -> new PersonRoleNotFoundException("Role de usuário não encontrada!"));
		
		EntityModel<PersonRole> personRoleModel = assembler.toModel(personrole);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(personRoleModel);
	}
	
	
	@PostMapping("/personrole")
	@Operation(summary= "Cria uma role de pessoa")
	public ResponseEntity<Void> postPersonRole(@RequestBody PersonRole personRole){
		
		if(personRole != null) {
			personRoleService.postPersonRole(personRole);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidPersonRoleException("Role de usuário inválida!");
	}
	
	@DeleteMapping("/personrole/{id}")
	@Operation(summary= "Deleta uma role de pessoa pelo ID")
	public ResponseEntity<Void> deletePersonRole(@PathVariable Long id){
		
		if(id != null) {
			personRoleService.deletePersonRole(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidPersonRoleException("Role de usuário inválida!");
	}
	
	@PutMapping("/personrole/{id}")
	@Operation(summary= "Atualiza uma role de pessoa pelo ID")
	public ResponseEntity<Void> updatePersonRole(@PathVariable Long id, @RequestBody PersonRole personRole){
		
		if(id != null && personRole != null) {
			personRoleService.updatePersonRole(id, personRole);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidPersonRoleException("Role de usuário inválida!");
	}
}
