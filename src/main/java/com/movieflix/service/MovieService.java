package com.movieflix.service;

import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
   private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }


}
