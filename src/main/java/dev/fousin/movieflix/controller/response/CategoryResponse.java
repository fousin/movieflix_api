package dev.fousin.movieflix.controller.response;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String nome) {
}
