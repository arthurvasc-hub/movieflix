package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
   private final MovieRepository repository;
   private final CategoryService categoryService;
   private final StreamingService streamingService;

    public MovieService(MovieRepository repository, CategoryService categoryService, StreamingService streamingService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
    }

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public List<Category> findCategories(List<Category> categories){
        List<Category> categoryFound = new ArrayList<>();
        categories.forEach(category -> {
            Category foundCategory = categoryService.findById(category.getId());
            if (foundCategory != null) {
                categoryFound.add(foundCategory);
            }
        });

        return categoryFound;
    }

    public List<Streaming> findStreamings(List<Streaming> streamings){
        List<Streaming> streamingFound = new ArrayList<>();
        streamings.forEach(streaming -> {
            Streaming foundStreaming = streamingService.findById(streaming.getId());
            if (foundStreaming != null) {
                streamingFound.add(foundStreaming);
            }
        });

        return streamingFound;
    }



}
