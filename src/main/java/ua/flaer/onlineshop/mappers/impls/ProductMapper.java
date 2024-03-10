package ua.flaer.onlineshop.mappers.impls;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.entities.Product;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    private ModelMapper modelMapper;

    public ProductMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }
}
