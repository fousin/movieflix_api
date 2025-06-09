package dev.fousin.movieflix.service;

import dev.fousin.movieflix.controller.request.CategoryRequest;
import dev.fousin.movieflix.controller.response.CategoryResponse;
import dev.fousin.movieflix.entity.Category;
import dev.fousin.movieflix.mapper.CategoryMapper;
import dev.fousin.movieflix.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<CategoryResponse> findAllCategories() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = CategoryMapper.toCategory(categoryRequest);
        Category savedCategory = repository.save(category);
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}
