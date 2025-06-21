package com.nisum.productdashboard.repository;

import com.nisum.productdashboard.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    void testFindByCategory() {
        Product p = new Product(null, "Watch", "Digital Watch", new BigDecimal("499.99"), 50, "Accessories", null, null);
        repository.save(p);
        List<Product> results = repository.findByCategory("Accessories");
        assertThat(results).hasSize(1);
    }

    @Test
    void testFindByPriceBetween() {
        Product p = new Product(null, "Shoes", "Running shoes", new BigDecimal("1500.00"), 20, "Footwear", null, null);
        repository.save(p);
        List<Product> results = repository.findByPriceBetween(new BigDecimal("1000"), new BigDecimal("2000"));
        assertThat(results).hasSize(1);
    }
}
