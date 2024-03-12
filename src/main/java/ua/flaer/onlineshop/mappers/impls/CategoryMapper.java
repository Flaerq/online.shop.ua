package ua.flaer.onlineshop.mappers.impls;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.CategoryDto;
import ua.flaer.onlineshop.model.entities.Category;
import ua.flaer.onlineshop.repositories.CategoryRepository;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private CategoryRepository categoryRepository;

    public CategoryMapper(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto mapTo(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public Category mapFrom(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .products(categoryRepository.findById(categoryDto.getId()).get().getProducts())
                .build();
    }
}
