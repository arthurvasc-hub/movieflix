package com.movieflix.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
public class MovieDTO {

    private Long id;
    @NotBlank(message = "O título não pode estar vazio")
    private String title;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String description;

    @NotNull(message = "A data de lançamento não pode ser nula")
    private LocalDate releaseDate;

    @Min(value = 0, message = "A nota deve ser no mínimo 0")
    @Max(value = 10, message = "A nota deve ser no máximo 10")
    private double rating;

    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;

    private List<Long> categories;
    private List<Long> streamings;

    public MovieDTO(Long id, String title, String description, LocalDate releaseDate, double rating, LocalDateTime updatedAt, LocalDateTime createdAt, List<Long> categories, List<Long> streamings) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.categories = categories;
        this.streamings = streamings;
    }
    public MovieDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "O título não pode estar vazio") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "O título não pode estar vazio") String title) {
        this.title = title;
    }

    public @NotBlank(message = "A descrição não pode estar vazia") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "A descrição não pode estar vazia") String description) {
        this.description = description;
    }

    public @NotNull(message = "A data de lançamento não pode ser nula") LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull(message = "A data de lançamento não pode ser nula") LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Min(value = 0, message = "A nota deve ser no mínimo 0")
    @Max(value = 10, message = "A nota deve ser no máximo 10")
    public double getRating() {
        return rating;
    }

    public void setRating(@Min(value = 0, message = "A nota deve ser no mínimo 0") @Max(value = 10, message = "A nota deve ser no máximo 10") double rating) {
        this.rating = rating;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Long> getCategories() {
        return categories;
    }

    public void setCategories(List<Long> categories) {
        this.categories = categories;
    }

    public List<Long> getStreamings() {
        return streamings;
    }

    public void setStreamings(List<Long> streamings) {
        this.streamings = streamings;
    }
}
