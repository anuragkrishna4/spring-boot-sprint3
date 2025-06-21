package com.nisum.ecommerce.service;

import com.nisum.ecommerce.exception.ProductNotFoundException;
import com.nisum.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        validate(product);
        products.add(product);
    }

    public Product getProductById(int id) {
        return findProduct(id);
    }

    public void updateProduct(int id, Product updated) {
        validate(updated);
        Product existing = findProduct(id);
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());
        existing.setCategory(updated.getCategory());
    }

    public void deleteProduct(int id) {
        Product p = findProduct(id);
        products.remove(p);
    }

    public List<Product> getProducts(String category, Double minPrice, Double maxPrice,
                                     int page, int size, String sort, String order) {

        List<Product> result = new ArrayList<>(products);

        if (category != null && !category.isBlank()) {
            result = result.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        if (minPrice != null) {
            result = result.stream().filter(p -> p.getPrice() >= minPrice).collect(Collectors.toList());
        }
        if (maxPrice != null) {
            result = result.stream().filter(p -> p.getPrice() <= maxPrice).collect(Collectors.toList());
        }


        if (sort != null) {
            Comparator<Product> comparator = switch (sort.toLowerCase()) {
                case "price" -> Comparator.comparing(Product::getPrice);
                case "name" -> Comparator.comparing(Product::getName);
                default -> Comparator.comparing(Product::getId);
            };
            if ("desc".equalsIgnoreCase(order)) comparator = comparator.reversed();
            result.sort(comparator);
        }

        int start = page * size;
        int end = Math.min(start + size, result.size());
        if (start >= result.size()) return Collections.emptyList();
        return result.subList(start, end);
    }
    private void validate(Product p) {
        if (p.getId() == null)
            throw new IllegalArgumentException("ID is required");
        if (p.getName() == null || p.getName().isBlank())
            throw new IllegalArgumentException("Name required");
        if (p.getPrice() < 0)
            throw new IllegalArgumentException("Price can't be negative");
        if (p.getStockQuantity() < 0)
            throw new IllegalArgumentException("Stock can't be negative");
        if (p.getCategory() == null || p.getCategory().isBlank())
            throw new IllegalArgumentException("Category required");
    }

    private Product findProduct(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product ID " + id + " not found"));
    }
}
