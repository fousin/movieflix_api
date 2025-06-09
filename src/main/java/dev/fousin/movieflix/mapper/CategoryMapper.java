package dev.fousin.movieflix.mapper;

import dev.fousin.movieflix.controller.request.CategoryRequest;
import dev.fousin.movieflix.controller.response.CategoryResponse;
import dev.fousin.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryResponse){
            return Category.builder()
                    .name(categoryResponse.name())
                    .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .nome(category.getName())
                .build();

    }

}
