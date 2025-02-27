package com.movieflix.mapper;

import com.movieflix.DTOs.CategoryDTO;
import com.movieflix.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static Category toCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        return category;
    }

    public static CategoryDTO toCategoryDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }
}
