package dev.fousin.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        String title,
        String description,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate releaseDate,

        Double rating,
        List<Long> categories,
        List<Long> streamings
) {
}
