package com.nisum.productapi;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();

    @GetMapping
    public List<Product> getAll() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
    }

    @PostMapping
    public String addProduct(@RequestBody Product product) {
        products.add(product);
        return "Product added";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product updated) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(updated.getName());
                p.setPrice(updated.getPrice());
                return "Product updated";
            }
        }
        throw new ProductNotFoundException("Product with ID " + id + " not found");
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);
        if (!removed) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }
        return "Product deleted";
    }
}
