package dev.fousin.movieflix.service;

import dev.fousin.movieflix.controller.request.MovieRequest;
import dev.fousin.movieflix.controller.response.MovieResponse;
import dev.fousin.movieflix.entity.Category;
import dev.fousin.movieflix.entity.Movie;
import dev.fousin.movieflix.entity.Streaming;
import dev.fousin.movieflix.mapper.MovieMapper;
import dev.fousin.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository repository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;

    public MovieResponse save(MovieRequest categoryRequest) {
        Movie category = MovieMapper.toEntity(categoryRequest);
        Movie savedMovie = repository.save(category);
        savedMovie.setCategories(this.findCategories(savedMovie.getCategories()));
        savedMovie.setStreamings(this.findStreamings(savedMovie.getStreamings()));
        return MovieMapper.toResponse(savedMovie);
    }

    public List<MovieResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }

    public List<MovieResponse> getByCategory(Long categoryId) {
        return repository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()))
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }

    public Optional<Movie> update(Long id, Movie updateMovie) {
        Optional<Movie> optMovie = repository.findById(id);
        if(!optMovie.isPresent()){
            return Optional.empty();
        }

        List<Category> categories = this.findCategories(updateMovie.getCategories());
        List<Streaming> streamings = this.findStreamings(updateMovie.getStreamings());

        Movie movie = optMovie.get();
        movie.setTitle(updateMovie.getTitle());
        movie.setDescription(updateMovie.getDescription());
        movie.setReleaseDate(updateMovie.getReleaseDate());
        movie.setRating(updateMovie.getRating());

        movie.getCategories().clear();
        movie.getStreamings().clear();

        movie.getCategories().addAll(categories);
        movie.getStreamings().addAll(streamings);

         repository.save(movie);

         return Optional.of(movie);
    }

    public Optional<Movie> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private List<Category> findCategories(List<Category> categories) {
        List<Category> categoriesFound = new ArrayList<>();
        categories.forEach(category -> categoryService.getCategoryById(category.getId())
                .ifPresent(categoriesFound::add)
        );

        return categoriesFound;
    }

    private List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.getById(streaming.getId())
                .ifPresent(streamingsFound::add)
        );
        return streamingsFound;
    }
}
