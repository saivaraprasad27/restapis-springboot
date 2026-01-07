package com.learn.DecProjectModule.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Product extends BaseModel{

    /**
     * Model
     * Holds data and business logic
     * Example: User, Product, Order
     * Talks to the database
     * -----------------------------
     * View (UI)
     * What the user sees
     * HTML, JSP, Thymeleaf, etc.
     */

    private String title;
    private String description;
    private Double price;
    private String imageUrl;

    @ManyToOne
    private Category category;

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                '}';
    }
}

