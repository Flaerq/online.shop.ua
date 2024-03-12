package ua.flaer.onlineshop.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.mappers.RequestMapper;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.dto.ProductRequestDto;
import ua.flaer.onlineshop.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    private RequestMapper<ProductDto, ProductRequestDto> productRequestMapper;

    public ProductController(ProductService productService,
                             RequestMapper<ProductDto, ProductRequestDto> productRequestMapper){
        this.productService = productService;
        this.productRequestMapper = productRequestMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getProducts();

        if(products.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id){
        Optional<ProductDto> product = productService.findById(id);

        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }

    @PostMapping
     public ResponseEntity<ProductDto> createProduct(@RequestBody ProductRequestDto product){
        ProductDto savedProduct = productService.createProduct(productRequestMapper.mapFrom(product));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId,
                                                    @RequestBody ProductRequestDto product){
        if (!productService.isExist(productId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto updatedProduct = productService.fullUpdateById(productId, productRequestMapper.mapFrom(product));

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> partialUpdateProduct(@PathVariable("id") Long productId,
                                                           @RequestBody ProductRequestDto product){
        if (!productService.isExist(productId)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductDto updatedProduct = productService.partialUpdate(productId, productRequestMapper.mapFrom(product));

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long productId){
        if (!productService.isExist(productId)){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        productService.delete(productId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
