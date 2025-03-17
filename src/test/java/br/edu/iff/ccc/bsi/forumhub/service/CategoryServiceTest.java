package br.edu.iff.ccc.bsi.forumhub.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
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
	CategoryService categoryService;
	
	@Mock
	private CategoryRepository categoryRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	 
    @Test
    @DisplayName("Busca por Id em Category com sucesso.")
    void testFindById() {
         Category mockedCategory = new Category(1L, "Category", LocalDateTime.now(), "1234");
         when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockedCategory));
        
        Category result = categoryService.findOne(1L).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        assertNotNull(result);
        assertEquals(mockedCategory.getName(), result.getName());
        verify(categoryRepository).findById(1L);
        
    }
}
