package ua.flaer.onlineshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.mappers.Mapper;
import ua.flaer.onlineshop.mappers.impls.CategoryMapper;
import ua.flaer.onlineshop.model.dto.CategoryDto;
import ua.flaer.onlineshop.model.dto.ProductDto;
import ua.flaer.onlineshop.model.entities.Category;
import ua.flaer.onlineshop.model.entities.Product;
import ua.flaer.onlineshop.services.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;
    private Mapper<Category, CategoryDto> categoryMapper;
    private Mapper<Product, ProductDto> productMapper;

    public CategoryController(CategoryService categoryService,
                              Mapper<Category, CategoryDto> categoryMapper,
                              Mapper<Product, ProductDto> productMapper){
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category){
        CategoryDto savedCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getListOfCategories(){

        List<CategoryDto> categories = categoryService.getCategories();

        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id){
         Optional<CategoryDto> category = categoryService.findById(id);

         if (category.isEmpty()){
             return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
         }

         return ResponseEntity.status(HttpStatus.OK).body(category.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> fullUpdateCategory(@RequestBody CategoryDto category,
                                                          @PathVariable("id") Long id){
        if (!categoryService.isExist(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.fullUpdateById(id, category));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDto> partialCategoryUpdate(@RequestBody CategoryDto category,
                                                             @PathVariable("id") Long id){
        if (!categoryService.isExist(id)){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.partialUpdate(id, category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id){
        categoryService.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable("id") Long categoryId){
        Optional<CategoryDto> category = categoryService.findById(categoryId);
        if(category.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<ProductDto> products = categoryMapper.mapFrom(category.get()).getProducts().stream()
                    .map(productMapper::mapTo)
                    .collect(Collectors.toList());
            if(products.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products,HttpStatus.OK);
        }

    }

}
