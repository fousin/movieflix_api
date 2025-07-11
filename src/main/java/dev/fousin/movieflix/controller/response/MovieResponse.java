package dev.fousin.movieflix.controller.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        Double rating,
        List<CategoryResponse> categories,
        List<StreamingResponse> streamings,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,

        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
