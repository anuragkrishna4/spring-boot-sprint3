package com.nisum.ecommerce.controller;

import com.nisum.ecommerce.model.Product;
import com.nisum.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String sort,
            @RequestParam(defaultValue = "asc") String order
    ) {
        return ResponseEntity.ok(service.getProducts(category, minPrice, maxPrice, page, size, sort, order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        service.addProduct(product);
        return ResponseEntity.ok("Product added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody Product product) {
        service.updateProduct(id, product);
        return ResponseEntity.ok("Product updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
