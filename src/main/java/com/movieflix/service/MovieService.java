package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
   private final MovieRepository repository;
   private final CategoryService categoryService;
   private final StreamingService streamingService;
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository repository, CategoryService categoryService, StreamingService streamingService, MovieRepository movieRepository) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.streamingService = streamingService;
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie){
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreamings(this.findStreamings(movie.getStreamings()));
        return repository.save(movie);
    }

    public List<Movie> findAll(){
        return repository.findAll();
    }

    public Optional<Movie> findById(Long id){
        Optional<Movie> movieById = repository.findById(id);
        if(movieById.isPresent()){
            return movieById;
        }
        return Optional.empty();
    }

    public List<Movie> findByCategories(Long categoryId) {
        return movieRepository.findMovieByCategories(List.of(new Category(categoryId, null)));
    }

    public Optional<Movie> update(Long id, Movie updateMovie){
        Optional<Movie> optMovie = repository.findById(id);
        if(optMovie.isPresent()){

            List<Category> categories = this.findCategories(updateMovie.getCategories());
            List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

            Movie movie = optMovie.get();
            movie.setId(id);
            movie.setTitle(updateMovie.getTitle());
            movie.setDescription(updateMovie.getDescription());
            movie.setRating(updateMovie.getRating());

            movie.getCategories().clear();
            movie.getCategories().addAll(categories);

            movie.getStreamings().clear();
            movie.getStreamings().addAll(streamings);

            repository.save(movie);

            return Optional.of(movie);

        }

        return Optional.empty();

    }

    public void deleteById(Long movieId){
        repository.deleteById(movieId);
    }

    private List<Category> findCategories(List<Category> categories){
        List<Category> categoryFound = new ArrayList<>();
        categories.forEach(category -> {
            Category foundCategory = categoryService.findById(category.getId());
            if (foundCategory != null) {
                categoryFound.add(foundCategory);
            }
        });

        return categoryFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings){
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
