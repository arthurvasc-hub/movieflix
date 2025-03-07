package com.movieflix.mapper;

import com.movieflix.DTOs.MovieDTO;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.CategoryRepository;
import com.movieflix.repository.StreamingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private static  CategoryRepository categoryRepository;
    private static  StreamingRepository streamingRepository;

    @Autowired
    public MovieMapper(CategoryRepository categoryRepository, StreamingRepository streamingRepository) {
        this.categoryRepository = categoryRepository;
        this.streamingRepository = streamingRepository;
    }

    public static Movie toMovie(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setRating(movieDTO.getRating());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        movie.setCreatedAt(movieDTO.getCreatedAt());

        // Busca as entidades diretamente pelos IDs
        List<Category> categories = categoryRepository.findAllById(movieDTO.getCategories());
        List<Streaming> streamings = streamingRepository.findAllById(movieDTO.getStreamings());

        movie.setCategories(categories);
        movie.setStreamings(streamings);
        return movie;
    }

    public static MovieDTO toMovieDTO(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setRating(movie.getRating());
        movieDTO.setUpdatedAt(movie.getUpdatedAt());
        movieDTO.setCreatedAt(movie.getCreatedAt());

        // Mapeando as listas de Category e Streaming para listas de IDs (Long)
        List<Long> categoryIds = movie.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());
        List<Long> streamingIds = movie.getStreamings().stream()
                .map(Streaming::getId)
                .collect(Collectors.toList());

        movieDTO.setCategories(categoryIds);
        movieDTO.setStreamings(streamingIds);

        return movieDTO;
    }
}

