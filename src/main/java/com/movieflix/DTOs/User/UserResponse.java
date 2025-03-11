package com.movieflix.DTOs.User;

import lombok.Builder;

@Builder
public record UserResponse(Long id, String name, String email) {
}
