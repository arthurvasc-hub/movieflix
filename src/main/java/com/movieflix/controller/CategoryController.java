package com.movieflix.controller;

import com.movieflix.DTOs.CategoryDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDTO> findAll(){
        List<Category> categoryList = categoryService.findAll();
        return categoryList.stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable Long id){
        Category categoryById = categoryService.findById(id);
                return CategoryMapper.toCategoryDTO(categoryById);

    }

    @PostMapping("/create")
    public CategoryDTO createCategory(@RequestBody CategoryDTO request){
        Category requestToCategory = CategoryMapper.toCategory(request);
        Category categorySaved = categoryService.saveCategory(requestToCategory);
        return CategoryMapper.toCategoryDTO(categorySaved);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }



}
