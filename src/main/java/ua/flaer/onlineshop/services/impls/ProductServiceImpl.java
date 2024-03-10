package ua.flaer.onlineshop.services.impls;

import org.springframework.stereotype.Service;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.impls.ProductMapper;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.entities.Product;
import ua.flaer.onlineshop.repositories.ProductRepository;
import ua.flaer.onlineshop.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private Mapper<Product, ProductDto> productMapper;


    public ProductServiceImpl(ProductRepository productRepository,
                              Mapper<Product,ProductDto> productMapper){
        this.productMapper = productMapper;
        this.productRepository = productRepository;
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
}
