package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@InjectMocks
	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Busca todas as categorias com sucesso.")
	public void testFindAll() {
		List<Category> mockedCategories = categoryService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma categoria encontrada!"));
		when(categoryRepository.findAll()).thenReturn(mockedCategories);

		List<Category> result = categoryService.findAll()
				.orElseThrow(() -> new RuntimeException("Nenhuma categoria encontrada!"));

		assertNotNull(result);
		assertEquals(mockedCategories.size(), result.size());
	}

	@Test
	@DisplayName("Busca por Id em Category com sucesso.")
	public void testFindOne() {
		Category mockedCategory = new Category(1L, "Category", LocalDateTime.now(), "1234");
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockedCategory));

		Category result = categoryService.findOne(1L)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

		assertNotNull(result);
		assertEquals(mockedCategory.getName(), result.getName());
		assertEquals(mockedCategory.getDescription(), result.getDescription());
		assertEquals(mockedCategory.getCreationDate(), result.getCreationDate());
		verify(categoryRepository).findById(1L);
	}

	@Test
	@DisplayName("Cria categoria com sucesso.")
	public void testPostCategory() {
		Category mockedCategory = new Category(1L, "Category", LocalDateTime.now(), "1234");
		categoryService.postCategory(mockedCategory);

		verify(categoryRepository).save(mockedCategory);
	}

	@Test
	@DisplayName("Deleta categoria com sucesso.")
	public void testDeleteCategory() {
		categoryService.deleteCategory(1L);

		verify(categoryRepository).deleteById(1L);
	}

	@Test
	@DisplayName("Atualiza categoria com sucesso.")
	public void testUpdateCategory() {
		Category existingCategory = new Category(1L, "Category", LocalDateTime.now(), "1234");
		Category updatedCategory = new Category(1L, "Category", LocalDateTime.now(), "1234");

		when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
		when(categoryRepository.save(updatedCategory)).thenReturn(updatedCategory);
		
		categoryService.updateCategory(1L, updatedCategory);
		
		verify(categoryRepository).save(updatedCategory);
	}
}
