package com.movieflix.DTOs;


import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StreamingDTO {

    private Long id;
    private String name;

    public StreamingDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public StreamingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
