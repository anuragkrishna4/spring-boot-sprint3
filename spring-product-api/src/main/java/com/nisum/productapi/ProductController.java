package com.nisum.productapi;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Product> productList = new ArrayList<>();


    @GetMapping
    public List<Product> getAllProducts() {
        return productList;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productList.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        productList.add(product);
        return "Product added successfully";
    }


    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product p : productList) {
            if (p.getId() == id) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
                return "Product updated successfully";
            }
        }
        return "Product not found";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean removed = productList.removeIf(p -> p.getId() == id);
        return removed ? "Product deleted successfully" : "Product not found";
    }
}
