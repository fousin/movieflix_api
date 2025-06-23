package dev.fousin.movieflix.controller;

import dev.fousin.movieflix.controller.request.MovieRequest;
import dev.fousin.movieflix.controller.response.MovieResponse;
import dev.fousin.movieflix.entity.Movie;
import dev.fousin.movieflix.mapper.MovieMapper;
import dev.fousin.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
public class MovieController {
    @Autowired
    private MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@RequestBody MovieRequest request) {
        MovieResponse movie = service.save(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> getByCategory(@RequestParam Long category) {
        return ResponseEntity.ok(service.getByCategory(category));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return service.getById(id)
                .map( movie -> ResponseEntity.ok(
                        MovieMapper.toResponse(movie))
                ).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @RequestBody MovieRequest request) {
        return service.update(id, MovieMapper.toEntity(request))
                .map( movie -> ResponseEntity.ok(MovieMapper.toResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Movie> opt = service.getById(id);
        if(!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
