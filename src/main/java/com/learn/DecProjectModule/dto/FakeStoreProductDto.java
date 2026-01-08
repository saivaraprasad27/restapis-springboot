package com.learn.DecProjectModule.dto;

import com.learn.DecProjectModule.models.Category;
import com.learn.DecProjectModule.models.Product;

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    //This will get the product of implementation using the values from fake store
    public Product getProduct(){
//        Product product = new Product();
//        product.setId(id);
//        product.setTitle(title);
//        product.setPrice(price);
//        product.setDescription(description);
//        product.setImageUrl(image);
//
//        Category category1 = new Category();
//        category1.setTitle(category);
//
//        product.setCategory(category1);

        return null;
    }

    @Override
    public String toString() {
        return "FakeStoreProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
