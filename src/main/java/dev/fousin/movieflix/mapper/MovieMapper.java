package dev.fousin.movieflix.mapper;

import dev.fousin.movieflix.controller.request.MovieRequest;
import dev.fousin.movieflix.controller.response.CategoryResponse;
import dev.fousin.movieflix.controller.response.MovieResponse;
import dev.fousin.movieflix.controller.response.StreamingResponse;
import dev.fousin.movieflix.entity.Category;
import dev.fousin.movieflix.entity.Movie;
import dev.fousin.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {


    public static Movie toEntity(MovieRequest request) {
        List<Category> categories = request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings = request.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .streamings(streamings)
                .categories(categories)
                .build();
    }

    public static MovieResponse toResponse(Movie movie) {
        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .streamings(streamings)
                .categories(categories)
                .createdAt(movie.getCreatedAt())
                .updatedAt(movie.getUpdatedAt())
                .build();
    }
}
