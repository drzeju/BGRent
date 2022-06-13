package com.example.BGRent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryRepository categoryRepository;

    // http://localhost:8080/api/categories
    //
    @GetMapping(
            value = "/api/categories",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<List<CategoryEntity>> getCategory() {
        return  ResponseEntity.ok(this.categoryRepository.findAll());
    }

    // http://localhost:8080/api/categories/1
    //
    @GetMapping(
            value = "/api/categories/{categoryId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryEntity> getCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.of(this.categoryRepository.findById(categoryId));
    }

    // POST http://localhost:8080/api/categories/create
    // echo '{"categoryName":"Deckbuilder"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/categories/create
    //
    @PostMapping(
            value = "/api/categories/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<CategoryEntity> createCategory(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(this.categoryRepository.save(categoryEntity));
    }

    // POST http://localhost:8080/api/categories/1/update
    // echo '{"categoryName":"Deckbuilder"}' | curl -X POST -H "Content-Type: application/json" -d @- http://localhost:8080/api/categories/1/update
    //
    @PostMapping(
            value = "/api/categories/{categoryId}/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryEntity newCategoryEntity) {
        Optional<CategoryEntity> foundCategoryOptional = this.categoryRepository.findById(categoryId);
        if (foundCategoryOptional.isPresent()) {
            CategoryEntity foundCategoryEntity = foundCategoryOptional.get();
            foundCategoryEntity.setCategoryName(newCategoryEntity.getCategoryName());
            this.categoryRepository.save(foundCategoryEntity);
        }
        return ResponseEntity.of(foundCategoryOptional);
    }

    // http://localhost:8080/api/categories/1/remove
    //
    @GetMapping(
            value = "/api/categories/{categoryId}/remove",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Transactional
    public ResponseEntity<CategoryEntity> removeCategory(@PathVariable("categoryId") Long categoryId) {
        Optional<CategoryEntity> foundCategoryOptional = this.categoryRepository.findById(categoryId);
        if (foundCategoryOptional.isPresent()) {
            this.categoryRepository.deleteById(categoryId);
        }
        return ResponseEntity.of(foundCategoryOptional);
    }
}