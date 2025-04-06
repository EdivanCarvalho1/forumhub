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

import br.edu.iff.ccc.bsi.forumhub.assembler.CategoryModel;
import br.edu.iff.ccc.bsi.forumhub.exception.CategoryNotFoundException;
import br.edu.iff.ccc.bsi.forumhub.exception.EmptyListException;
import br.edu.iff.ccc.bsi.forumhub.exception.InvalidCategoryException;
import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Category", description= "Operações relacionadas a categorias")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryModel assembler;
	
	@GetMapping("/category")
	@Operation(summary= "Retorna todas as categorias")
	public ResponseEntity<CollectionModel<EntityModel<Category>>> getCategories() {
		List<EntityModel<Category>> categories = categoryService.findAll()
			.orElseThrow(() -> new CategoryNotFoundException())
			.stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());
		
		if (categories.isEmpty()) {
			throw new EmptyListException("Nenhuma categoria cadastrada");
		}
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(CollectionModel.of(categories));
    }

	
	@GetMapping("/category/{id}")
	@Operation(summary= "Retorna uma categoria pelo ID")
	public ResponseEntity<EntityModel<Category>> getCategory(@PathParam(value="id") Long id){
		
		Category category = categoryService.findOne(id)
				.orElseThrow(() -> new CategoryNotFoundException(id));
		    
		EntityModel<Category> categoryModel = assembler.toModel(category);
		
		return ResponseEntity.ok(categoryModel);
		
	}
	@PostMapping("/category")
	@Operation(summary= "Cria uma categoria")
	public ResponseEntity<Void> postCategory(@RequestBody Category category){
		
		if(category != null) {
			categoryService.postCategory(category);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		throw new InvalidCategoryException();
		
	}
	
	@DeleteMapping("/category/{id}")
	@Operation(summary= "Deleta uma categoria pelo ID")
	public ResponseEntity<Void> deleteCategory(@PathParam(value = "id") Long id){
		
		if(id != null) {
			categoryService.deleteCategory(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidCategoryException();
	}
	
	@PutMapping("/category/{id}")
	@Operation(summary= "Atualiza uma categoria pelo ID")
	public ResponseEntity<Void> updateCategory(@PathParam(value = "id") Long id, @RequestBody Category category){
		
		if(id != null && category != null) {
			categoryService.updateCategory(id, category);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		throw new InvalidCategoryException();
	}
}
