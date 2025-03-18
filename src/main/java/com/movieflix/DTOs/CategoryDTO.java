package com.movieflix.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component

public class CategoryDTO {

    private Long id;
    @NotEmpty(message = "Nome da categoria é obrigatório.")
    private String name;

}
