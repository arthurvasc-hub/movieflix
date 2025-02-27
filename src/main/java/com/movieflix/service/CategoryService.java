package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> categoryFound = repository.findById(id);
        return categoryFound.orElse(null);

    }

    public Category save(Category category){
        return repository.save(category);
    }

    public void delete (Long id){
        repository.deleteById(id);
        System.out.println("Category com o id: " + id + " deletado com sucesso.");
    }

}
