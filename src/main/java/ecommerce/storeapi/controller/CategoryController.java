package ecommerce.storeapi.controller;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.Product;
import ecommerce.storeapi.repository.CategoryRepository;
import ecommerce.storeapi.repository.ProductRepository;
import ecommerce.storeapi.service.CategoryService;
import ecommerce.storeapi.service.IkonkaProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<GetAllCategoriesResponse> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/link-categories")
    public ResponseEntity<LinkCategoriesResponse> linkCategories( @RequestBody LinkCategoriesRequest request) {
        return ResponseEntity.ok(categoryService.linkCategories(request));
    }

    @PostMapping("/unlink-categories")
    public ResponseEntity<UnlinkCategoriesResponse> unlinkCategories(@RequestBody UnlinkCategoriesRequest request) {
        return ResponseEntity.ok(categoryService.unlinkCategories(request));
    }

    @PostMapping("/add-category")
    public ResponseEntity<AddCategoryResponse> addCategory(@RequestBody AddCategoryRequest request) {
        return ResponseEntity.ok(categoryService.addCategory(request));
    }

    @DeleteMapping("/delete-category")
    public ResponseEntity<DeleteCategoryResponse> deleteCategory(@RequestBody DeleteCategoryRequest request) {
        return ResponseEntity.ok(categoryService.deleteCategory(request));
    }

}
