package com.movieflix.DTOs;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email) {
}
