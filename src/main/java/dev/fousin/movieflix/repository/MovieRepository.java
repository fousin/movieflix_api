package dev.fousin.movieflix.repository;

import dev.fousin.movieflix.entity.Category;
import dev.fousin.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findMovieByTitle(String title);
    List<Movie> findMovieByCategories(List<Category> categories);
    List<Movie> findMovieByOrderByRatingDesc();
}
