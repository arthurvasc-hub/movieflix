package com.movieflix.DTOs;


import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StreamingDTO {

    private Long id;
    private String name;

}
