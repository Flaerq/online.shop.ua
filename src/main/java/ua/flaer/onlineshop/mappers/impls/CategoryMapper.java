package ua.flaer.onlineshop.mappers.impls;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.CategoryDto;
import ua.flaer.onlineshop.model.entities.Category;

@Component
public class CategoryMapper implements Mapper<Category, CategoryDto> {

    private ModelMapper modelMapper;

    public CategoryMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto mapTo(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public Category mapFrom(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
