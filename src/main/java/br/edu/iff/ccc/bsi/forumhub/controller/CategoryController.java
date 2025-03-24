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

import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Person", description= "Operações relacionadas a categorias")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/category")
	@Operation(summary= "Retorna todas as categorias")
	public ResponseEntity<List<Category>> getCategorys(){
		
		List<Category> categoryList = categoryService.findAll().orElseThrow(() -> new RuntimeException("Nenhum usuário cadastrado"));
		
		return ResponseEntity.ok().body(categoryList);
		
	}
	
	@GetMapping("/category/{id}")
	@Operation(summary= "Retorna uma categoria pelo ID")
	public ResponseEntity<Category> getCategory(@PathParam(value="id") Long id){
		
		Category category = categoryService.findOne(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
		
		return ResponseEntity.ok().body(category);
	}
	@PostMapping("/category")
	@Operation(summary= "Cria uma categoria")
	public ResponseEntity<Void> postCategory(@RequestBody Category category){
		
		if(category != null) {
			categoryService.postCategory(category);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/category/{id}")
	@Operation(summary= "Deleta uma categoria pelo ID")
	public ResponseEntity<Void> deleteCategory(@PathParam(value = "id") Long id){
		
		if(id != null) {
			categoryService.deleteCategory(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping("/category/{id}")
	@Operation(summary= "Atualiza uma categoria pelo ID")
	public ResponseEntity<Void> updateCategory(@PathParam(value = "id") Long id, @RequestBody Category category){
		
		if(id != null) {
			categoryService.updateCategory(id, category);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
