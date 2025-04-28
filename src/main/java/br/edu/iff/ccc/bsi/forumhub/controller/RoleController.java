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
import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Role;
import br.edu.iff.ccc.bsi.forumhub.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
				.orElseThrow(() -> new NotFoundException("Nenhuma role cadastrada!")).stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(roleList));

	}

	@GetMapping("/role/{id}")
	@Operation(summary = "Retorna uma pessoa pelo ID")
	public ResponseEntity<EntityModel<Role>> getRole(@Valid @PathParam(value = "id") Long id) {

		Role role = roleService.findOne(id).orElseThrow(() -> new NotFoundException("Role não encontrada!"));

		EntityModel<Role> roleModel = assembler.toModel(role);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(roleModel);
	}

	@PostMapping("/role")
	@Operation(summary = "Cria um usuário")
	public ResponseEntity<Void> postRole(@Valid @RequestBody Role role) {

		roleService.postRole(role);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@DeleteMapping("/role/{id}")
	@Operation(summary = "Deleta uma pessoa pelo ID")
	public ResponseEntity<Void> deleteRole(@Valid @PathParam(value = "id") Long id) {

		roleService.deleteRole(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@PutMapping("/role/{id}")
	@Operation(summary = "Atualiza uma pessoa pelo ID")
	public ResponseEntity<Void> updateRole(@Valid @PathParam(value = "id") Long id, @RequestBody Role role) {

		roleService.updateRole(id, role);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
