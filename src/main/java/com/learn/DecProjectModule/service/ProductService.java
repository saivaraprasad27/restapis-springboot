package com.learn.DecProjectModule.service;

import com.learn.DecProjectModule.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);
    List<Product> getAllProducts();
    Product createProduct(Long id, String title,String description, double price, String category, String image);
}
