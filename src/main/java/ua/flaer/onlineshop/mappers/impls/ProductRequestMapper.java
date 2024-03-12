package ua.flaer.onlineshop.mappers.impls;

import org.springframework.stereotype.Component;
import ua.flaer.onlineshop.mappers.RequestMapper;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.dto.ProductRequestDto;

@Component
public class ProductRequestMapper implements RequestMapper<ProductDto, ProductRequestDto> {
    @Override
    public ProductDto mapFrom(ProductRequestDto productRequestDto) {
        return ProductDto.builder()
                .name(productRequestDto.getName())
                .description(productRequestDto.getDescription())
                .price(productRequestDto.getPrice())
                .categoryId(productRequestDto.getCategoryId())
                .build();
    }
}
