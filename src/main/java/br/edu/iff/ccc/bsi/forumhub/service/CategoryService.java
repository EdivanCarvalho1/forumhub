package br.edu.iff.ccc.bsi.forumhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.bsi.forumhub.exception.NotFoundException;
import br.edu.iff.ccc.bsi.forumhub.model.Category;
import br.edu.iff.ccc.bsi.forumhub.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	public Optional<List<Category>> findAll() {

		return Optional.ofNullable(categoryRepository.findAll());

	}

	public Optional<Category> findOne(Long id) {

		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("O comentário não existe"));

		return Optional.ofNullable(category);

	}

	public void postCategory(Category category) {

		categoryRepository.save(category);

	}

	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}

	public void updateCategory(Long id, Category category) {

		Category existingCategory = categoryRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("O tópico não existe"));
		
		existingCategory.setCreationDate(category.getCreationDate());
		existingCategory.setDescription(category.getDescription());
		existingCategory.setName(category.getName());
		
		categoryRepository.save(existingCategory);
	}
}
