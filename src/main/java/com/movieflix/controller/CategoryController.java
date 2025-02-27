package com.movieflix.controller;

import com.movieflix.DTOs.CategoryDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService categoryService) {
        this.service = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categories = service.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
        return ResponseEntity.ok(categories);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id){
        Category categoryById = service.findById(id);

        if(categoryById != null) {
        CategoryDTO category = CategoryMapper.toCategoryDTO(categoryById);
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(category);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO request){
        Category requestToCategory = CategoryMapper.toCategory(request);
        Category categorySaved = service.save(requestToCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.toCategoryDTO(categorySaved));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        Category categoryById = service.findById(id);
        if(categoryById != null){
            service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }



}
