package com.movieflix.controller;


import com.movieflix.DTOs.MovieDTO;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
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


}
