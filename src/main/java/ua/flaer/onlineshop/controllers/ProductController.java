package ua.flaer.onlineshop.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.services.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
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
     public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product){
        ProductDto savedProduct = productService.createProduct(product);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
