package com.movieflix.entity;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name="streaming")
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(length = 100, nullable = false)
    private String name;

    public Streaming(long id, String name) {
        Id = id;
        this.name = name;
    }

    public Streaming() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
