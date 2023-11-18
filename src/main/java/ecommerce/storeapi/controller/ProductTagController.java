package ecommerce.storeapi.controller;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.repository.CategoryRepository;
import ecommerce.storeapi.repository.ProductTagRepository;
import ecommerce.storeapi.service.CategoryService;
import ecommerce.storeapi.service.ProductTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/product-tag")
public class ProductTagController {
    private final ProductTagRepository productTagRepository;
    private final ProductTagService productTagService;

    public ProductTagController(ProductTagRepository productTagRepository, ProductTagService productTagService) {
        this.productTagRepository = productTagRepository;
        this.productTagService = productTagService;
    }

    @GetMapping("/get-all-product-tags")
    public ResponseEntity<GetAllProductTagsResponse> getAllProductTags() {
        return ResponseEntity.ok(productTagService.getAllProductTags());
    }

    @PostMapping("/add-product-tag")
    public ResponseEntity<AddProductTagResponse> addProductTag(@RequestBody AddProductTagRequest request) {
        return ResponseEntity.ok(productTagService.addProductTag(request));
    }

    @DeleteMapping("/delete-product-tag")
    public ResponseEntity<DeleteProductTagResponse> deleteProductTag(@RequestBody DeleteProductTagRequest request) {
        return ResponseEntity.ok(productTagService.deleteProductTag(request));
    }
}
