package com.nisum.demo.controller;

import com.nisum.demo.model.Product;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product) {
        products.add(product);
        return ResponseEntity.ok("Product created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @Valid @RequestBody Product product) {
        for(Product p : products) {
            if(p.getId() == id) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setCategory(product.getCategory());
                return ResponseEntity.ok("Product updated successfully!");
            }
        }
        return ResponseEntity.status(404).body("Product with id "+id +"not found!");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {


        return ResponseEntity.ok(products);
    }
}
