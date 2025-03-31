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
	RoleService roleService;

	@GetMapping("/role")
	@Operation(summary = "Retorna todas as pessoas/users")
	public ResponseEntity<List<Role>> getRoles() {

		List<Role> roleList = roleService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));

		return ResponseEntity.ok().body(roleList);

	}

	@GetMapping("/role/{id}")
	@Operation(summary = "Retorna uma pessoa pelo ID")
	public ResponseEntity<Role> getRole(@PathParam(value = "id") Long id) {

		Role role = roleService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		return ResponseEntity.ok().body(role);
	}

	@PostMapping("/role")
	@Operation(summary = "Cria um usuário")
	public ResponseEntity<Void> postRole(@RequestBody Role role) {

		if (role != null) {
			roleService.postRole(role);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@DeleteMapping("/role/{id}")
	@Operation(summary = "Deleta uma pessoa pelo ID")
	public ResponseEntity<Void> deleteRole(@PathParam(value = "id") Long id) {

		if (id != null) {
			roleService.deleteRole(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping("/role/{id}")
	@Operation(summary = "Atualiza uma pessoa pelo ID")
	public ResponseEntity<Void> updateRole(@PathParam(value = "id") Long id, @RequestBody Role role) {

		if (id != null) {
			roleService.updateRole(id, role);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
