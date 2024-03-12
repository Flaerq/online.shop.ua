package ua.flaer.onlineshop.services.impls;

import org.springframework.stereotype.Service;
import ua.flaer.onlineshop.exceptions.NoProductFoundException;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.impls.ProductMapper;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.entities.Product;
import ua.flaer.onlineshop.repositories.CategoryRepository;
import ua.flaer.onlineshop.repositories.ProductRepository;
import ua.flaer.onlineshop.services.CategoryService;
import ua.flaer.onlineshop.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private Mapper<Product, ProductDto> productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              Mapper<Product,ProductDto> productMapper){
        this.productMapper = productMapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDto createProduct(ProductDto product) {
        Product savedProduct = productRepository.save(productMapper.mapFrom(product));

        return productMapper.mapTo(savedProduct);
    }

    @Override
    public List<ProductDto> getProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(),false)
                .map(product -> productMapper.mapTo(product))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id)
                .map(product -> productMapper.mapTo(product));
    }

    @Override
    public boolean isExist(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public ProductDto fullUpdateById(Long id, ProductDto product) {
        product.setId(id);
        Product updatedProduct = productRepository.save(productMapper.mapFrom(product));

        return productMapper.mapTo(updatedProduct);
    }


    @Override
    public ProductDto partialUpdate(Long id, ProductDto product) {
        product.setId(id);

        Product updatedProduct = productRepository.findById(id)
                .map(bdProduct -> {
                    Optional.ofNullable(product.getName()).ifPresent(bdProduct::setName);
                    Optional.ofNullable(product.getDescription()).ifPresent(bdProduct::setDescription);
                    Optional.ofNullable(product.getPrice()).ifPresent(bdProduct::setPrice);
                    Optional.ofNullable(product.getCategoryId()).ifPresent((categoryId) -> {
                        bdProduct.setCategory(categoryRepository.findById(categoryId).get());
                    });
                    return bdProduct;
                }).get();

        productRepository.save(updatedProduct);

        return productMapper.mapTo(updatedProduct);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
