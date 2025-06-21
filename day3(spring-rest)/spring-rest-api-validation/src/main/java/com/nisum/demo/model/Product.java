package com.nisum.demo.model;

import com.nisum.demo.validation.ValidCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

public class Product {

    @NotNull(message = "ID cannot be null")
    private Integer id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 30, message = "Name must be 3 to 30 characters")
    private String name;

    @Min(value = 1, message = "Price must be greater than 0")
    private double price;

    @ValidCategory
    private String category;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
