package ecommerce.storeapi.controller;

import ecommerce.storeapi.connection.*;
import ecommerce.storeapi.model.Product;
import ecommerce.storeapi.repository.ProductRepository;
import ecommerce.storeapi.service.IkonkaProductService;
import ecommerce.storeapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final IkonkaProductService ikonkaProductService;

    public ProductController(ProductRepository productRepository, IkonkaProductService ikonkaProductService, ProductService productService ) {
        this.productRepository = productRepository;
        this.ikonkaProductService = ikonkaProductService;
        this.productService = productService;
    }

    //own products endpoints
    @GetMapping("/product")
    public ResponseEntity<GetProductResponse> getProduct(@RequestParam String id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping("/add-new-product")
    public ResponseEntity<AddProductResponse> addNewProduct(AddProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PostMapping("/update-product")
    public ResponseEntity<UpdateProductResponse> updateProduct(UpdateProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct((request)));
    }

    @DeleteMapping("/delete-product")
    public ResponseEntity<DeleteProductResponse> deleteProduct (DeleteProductRequest request) {
        return ResponseEntity.ok(productService.deleteProduct(request));
    }

    @PostMapping("/link-products")
    public ResponseEntity<LinkProductResponse> linkProducts(LinkProductRequest request) {
        return ResponseEntity.ok(productService.linkProductProduct(request));
    }

    @PostMapping("/unlink-products")
    public ResponseEntity<UnlinkProductResponse> unlinkProducts(UnlinkProductRequest request) {
        return ResponseEntity.ok(productService.unlinkProductProduct(request));
    }

    @PostMapping("/substitute-products")
    public ResponseEntity<SubstituteProductResponse> substituteProducts(SubstituteProductRequest request) {
        return ResponseEntity.ok(productService.substituteProduct(request));
    }

    @PostMapping("/cancel-substitute-products")
    public ResponseEntity<CancelSubstituteProductResponse> cancelSubstituteProducts(CancelSubstituteProductRequest request) {
        return ResponseEntity.ok(productService.cancelSubstituteProduct(request));
    }

    //ikonka products endpoints

    @GetMapping("/get-ikonka-product")
    public ResponseEntity<GetIkonkaProductResponse> getIkonkaProduct(String barcode) {
        return ResponseEntity.ok(ikonkaProductService.getIkonkaProduct(barcode));
    }

    @GetMapping("/fetch-all-ikonka-products")
    public ResponseEntity<FetchAllIkonkaProductsResponse> fetchAllIkonkaProducts() {
        return ResponseEntity.ok(ikonkaProductService.fetchAllIkonkaProducts());
    }

    @GetMapping("/transfer-all-ikonka-products-to-products")
    public ResponseEntity<TransferAllIkonkaProductsToProductsResponse> transferAllIkonkaProductsToProducts() {
        return ResponseEntity.ok(ikonkaProductService.transferAllIkonkaProductsToProducts());
    }

    @QueryMapping
    public Iterable<Product> products(){
        return this.productRepository.findAll();
    }

    @QueryMapping
    public Product productById(@Argument String id){
        return this.productRepository.findById(id).orElseThrow();
    }
}
