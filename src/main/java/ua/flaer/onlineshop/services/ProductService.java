package ua.flaer.onlineshop.services;

import ua.flaer.onlineshop.model.dto.CategoryDto;
import ua.flaer.onlineshop.model.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto createProduct(ProductDto product);

    List<ProductDto> getProducts();

    Optional<ProductDto> findById(Long id);

    boolean isExist(Long id);

    ProductDto fullUpdateById(Long id, ProductDto product);

    ProductDto partialUpdate(Long id, ProductDto product);

    void delete(Long id);

}
