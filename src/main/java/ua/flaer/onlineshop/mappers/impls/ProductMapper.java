package ua.flaer.onlineshop.mappers.impls;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.entities.Product;
import ua.flaer.onlineshop.repositories.CategoryRepository;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private CategoryRepository categoryRepository;

    public ProductMapper(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .categoryId(product.getCategory().getId())
                .build();
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .category(categoryRepository.findById(productDto.getCategoryId()).get())
                .build();
    }
}
