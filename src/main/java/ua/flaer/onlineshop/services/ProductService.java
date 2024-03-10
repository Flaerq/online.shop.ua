package ua.flaer.onlineshop.services;

import ua.flaer.onlineshop.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto createProduct(ProductDto product);

    List<ProductDto> getProducts();

    Optional<ProductDto> findById(Long id);

}
