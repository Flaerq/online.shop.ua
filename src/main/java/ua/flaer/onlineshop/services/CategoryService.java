package ua.flaer.onlineshop.services;

import ua.flaer.onlineshop.model.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);

    List<CategoryDto> getCategories();

    Optional<CategoryDto> findById(Long id);

    boolean isExist(Long id);

    CategoryDto fullUpdateById(Long id, CategoryDto category);

    CategoryDto partialUpdate(Long id, CategoryDto category);

}
