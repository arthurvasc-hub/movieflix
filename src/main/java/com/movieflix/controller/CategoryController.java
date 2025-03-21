package com.movieflix.controller;

import com.movieflix.DTOs.CategoryDTO;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/category")
@Tag(name = "Categorias", description = "Endpoints para gerenciamento das categorias")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService categoryService) {
        this.service = categoryService;
    }

    @Operation(summary = "Listar todas as categorias", description = "Retorna todas as categorias cadastradas no sistema")
    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categories = service.findAll()
                .stream()
                .map(CategoryMapper::toCategoryDTO)
                .toList();
        return ResponseEntity.ok(categories);
    }

    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria específica pelo seu ID")
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

    @Operation(summary = "Criar nova categoria", description = "Cria uma nova categoria no sistema")
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO request){
        Category requestToCategory = CategoryMapper.toCategory(request);
        Category categorySaved = service.save(requestToCategory);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoryMapper.toCategoryDTO(categorySaved));
    }

    @Operation(summary = "Deletar categoria por ID", description = "Deleta uma categoria específica pelo seu ID")
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


