package com.nisum.ecommerce.model;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
}
