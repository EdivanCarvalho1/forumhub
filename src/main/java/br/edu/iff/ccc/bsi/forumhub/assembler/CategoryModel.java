package br.edu.iff.ccc.bsi.forumhub.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.edu.iff.ccc.bsi.forumhub.controller.CategoryController;
import br.edu.iff.ccc.bsi.forumhub.model.Category;

@Component
public class CategoryModel implements RepresentationModelAssembler<Category, EntityModel<Category>> {


    @Override
    public EntityModel<Category> toModel(Category category) {
        return EntityModel.of(category,
                linkTo(methodOn(CategoryController.class).getCategory(category.getId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getCategories()).withRel("categories"));
    }

}
