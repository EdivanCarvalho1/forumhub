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

import br.edu.iff.ccc.bsi.forumhub.model.PersonRole;
import br.edu.iff.ccc.bsi.forumhub.service.PersonRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Person Role", description= "Operações relacionadas a roles de pessoas")
public class PersonRoleController {
	
	@Autowired
	PersonRoleService personRoleService;
	
	@GetMapping("/personrole")
	@Operation(summary= "Retorna todas as roles de pessoas")
	public ResponseEntity<List<PersonRole>> getPersonRoles(){
		
		List<PersonRole> personroleList = personRoleService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(personroleList);
		
	}
	
	@GetMapping("/personrole/{id}")
	@Operation(summary= "Retorna uma role de pessoa pelo ID")
	public ResponseEntity<PersonRole> getPersonRole(@PathParam(value="id") Long id){
		
		PersonRole personrole = personRoleService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(personrole);
	}
	
	
	@PostMapping("/personrole")
	@Operation(summary= "Cria uma role de pessoa")
	public ResponseEntity<Void> postPersonRole(@RequestBody PersonRole personRole){
		
		if(personRole != null) {
			personRoleService.postPersonRole(personRole);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/personrole/{id}")
	@Operation(summary= "Deleta uma role de pessoa pelo ID")
	public ResponseEntity<Void> deletePersonRole(@PathParam(value = "id") Long id){
		
		if(id != null) {
			personRoleService.deletePersonRole(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/personrole/{id}")
	@Operation(summary= "Atualiza uma role de pessoa pelo ID")
	public ResponseEntity<Void> updatePersonRole(@PathParam(value = "id") Long id, @RequestBody PersonRole personRole){
		
		if(id != null) {
			personRoleService.updatePersonRole(id, personRole);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
