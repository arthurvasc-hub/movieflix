package com.movieflix.controller;


import com.movieflix.DTOs.MovieDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@Tag(name = "Filmes", description = "Endpoints para gerenciamento de filmes")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @Operation(summary = "Salvar um novo filme", description = "Salva um novo filme no sistema")
    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDTO){
        Movie savedMovie = service.save(MovieMapper.toMovie(movieDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieDTO(savedMovie));
    }

    @Operation(summary = "Listar todos os filmes", description = "Retorna todos os filmes cadastrados no sistema")
    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll(){
        List<MovieDTO> movies = service.findAll()
                .stream()
                .map(MovieMapper::toMovieDTO)
                .toList();
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "Buscar filme por ID", description = "Retorna um filme específico pelo seu ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieDTO(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar filme por ID", description = "Atualiza as informações de um filme existente")
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@Valid @PathVariable Long id, @RequestBody MovieDTO movieDTO){
        return service.update(id, MovieMapper.toMovie(movieDTO))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieDTO(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar filmes por categoria", description = "Retorna filmes filtrados por uma categoria específica")
    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> findByCategory(@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategories(category)
                .stream()
                .map(MovieMapper::toMovieDTO)
                .toList());
    }

    @Operation(summary = "Deletar filme por ID", description = "Deleta um filme específico pelo seu ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Movie> optMovie = service.findById(id);
        if(optMovie.isPresent()){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

