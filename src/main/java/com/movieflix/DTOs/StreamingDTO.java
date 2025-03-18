package com.movieflix.DTOs;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StreamingDTO {

    private Long id;
    @NotEmpty(message = "Nome do serviço de streaming é obrigatório.")
    private String name;

}
