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

import br.edu.iff.ccc.bsi.forumhub.assembler.RoleModel;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidRoleException;
import br.edu.iff.ccc.bsi.forumhub.exception.RoleNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Role", description = "Operações relacionadas a roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleModel assembler;

	@GetMapping("/role")
	@Operation(summary = "Retorna todas as pessoas/users")
	public ResponseEntity<CollectionModel<EntityModel<Role>>> getRoles() {

		List<EntityModel<Role>> roleList = roleService.findAll()
				.orElseThrow(() -> new RoleNotFoundException("Nenhuma role cadastrada!"))
				.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(roleList));

	}

	@GetMapping("/role/{id}")
	@Operation(summary = "Retorna uma pessoa pelo ID")
	public ResponseEntity<EntityModel<Role>> getRole(@PathParam(value = "id") Long id) {

		Role role = roleService.findOne(id).orElseThrow(() -> new RoleNotFoundException("Role não encontrada!"));
		
		EntityModel<Role> roleModel = assembler.toModel(role);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(roleModel);
	}

	@PostMapping("/role")
	@Operation(summary = "Cria um usuário")
	public ResponseEntity<Void> postRole(@RequestBody Role role) {

		if (role != null) {
			roleService.postRole(role);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidRoleException("Usuário inválido");
	}

	@DeleteMapping("/role/{id}")
	@Operation(summary = "Deleta uma pessoa pelo ID")
	public ResponseEntity<Void> deleteRole(@PathParam(value = "id") Long id) {

		if (id != null) {
			roleService.deleteRole(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidRoleException("ID inválido");
	}

	@PutMapping("/role/{id}")
	@Operation(summary = "Atualiza uma pessoa pelo ID")
	public ResponseEntity<Void> updateRole(@PathParam(value = "id") Long id, @RequestBody Role role) {

		if (id != null && role != null) {
			roleService.updateRole(id, role);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidRoleException("ID inválido");
	}
}
