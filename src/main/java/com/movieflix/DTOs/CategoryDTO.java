package com.movieflix.DTOs;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryDTO {

    private Long id;
    private String name;

}
