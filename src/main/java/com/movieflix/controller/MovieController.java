package com.movieflix.controller;


import com.movieflix.DTOs.MovieDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService service;


    public MovieController(MovieService service, MovieService movieService) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO movieDTO){
        Movie savedMovie = service.save(MovieMapper.toMovie(movieDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toMovieDTO(savedMovie));
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> findAll(){
        List<MovieDTO> movies = service.findAll()
                .stream()
                .map(MovieMapper::toMovieDTO)
                .toList();

        return ResponseEntity.ok(movies);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return service.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieDTO(movie)))
                .orElse(ResponseEntity.notFound().build());
    }



    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO movieDTO){
        return service.update(id, MovieMapper.toMovie(movieDTO))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieDTO(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDTO>> findByCategory(@RequestParam Long category){
        return ResponseEntity.ok(service.findByCategories(category)
                .stream()
                .map(MovieMapper::toMovieDTO)
                .toList());
    }

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
