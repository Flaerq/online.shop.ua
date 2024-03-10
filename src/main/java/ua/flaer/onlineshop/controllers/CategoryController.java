package ua.flaer.onlineshop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.flaer.onlineshop.model.dto.CategoryDto;
import ua.flaer.onlineshop.services.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
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

}
