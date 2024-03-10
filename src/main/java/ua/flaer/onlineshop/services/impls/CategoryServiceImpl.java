package ua.flaer.onlineshop.services.impls;

import org.springframework.stereotype.Service;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.CategoryDto;
import ua.flaer.onlineshop.model.entities.Category;
import ua.flaer.onlineshop.repositories.CategoryRepository;
import ua.flaer.onlineshop.services.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private Mapper<Category, CategoryDto> categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               Mapper<Category, CategoryDto> categoryMapper){
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDto createCategory(CategoryDto categoryDto){

        Category savedCategory = categoryRepository.save(categoryMapper.mapFrom(categoryDto));

        return categoryMapper.mapTo(savedCategory);
    }

    @Override
    public List<CategoryDto> getCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        List<CategoryDto> result = new ArrayList<>();

        for (Category category : categories){
            result.add(categoryMapper.mapTo(category));
        }

        return result;
    }

    @Override
    public Optional<CategoryDto> findById(Long id) {
        Optional<Category> bdCategory = categoryRepository.findById(id);

        return bdCategory.map((category) -> categoryMapper.mapTo(category));
    }

    @Override
    public boolean isExist(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public CategoryDto fullUpdateById(Long id, CategoryDto category) {
        category.setId(id);
        return categoryMapper.mapTo(categoryRepository.save(categoryMapper.mapFrom(category)));
    }

    @Override
    public CategoryDto partialUpdate(Long id, CategoryDto category) {
        category.setId(id);

        Category updatedCategory = categoryRepository.findById(id).map(bdCategory -> {
            Optional.ofNullable(category.getName()).ifPresent(bdCategory::setName);
            Optional.ofNullable(category.getDescription()).ifPresent(bdCategory::setDescription);
            return bdCategory;
        }).get();

        categoryRepository.save(updatedCategory);

        return categoryMapper.mapTo(updatedCategory);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
