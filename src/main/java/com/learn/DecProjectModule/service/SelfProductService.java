package com.learn.DecProjectModule.service;

import com.learn.DecProjectModule.exceptions.ProductNotFoundException;
import com.learn.DecProjectModule.models.Category;
import com.learn.DecProjectModule.models.Product;
import com.learn.DecProjectModule.repository.CategoryRepository;
import com.learn.DecProjectModule.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductService (ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()){
            return p.get();
        }
        throw new ProductNotFoundException("Product is not found is database");
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Long id, String title, String description, double price, String categoryTitle, String image) {

        // 1. Check if category is there on db
        // 2. If no there, Create it and use it while saving product
        // 3. If there, use it in product directly
        Product p = new Product();
        Optional<Category> currentCategory = categoryRepository.findByTitle(categoryTitle);

        if(currentCategory.isEmpty()){
            //This means category is not present our db
            Category newCat = new Category();
            newCat.setTitle(categoryTitle);
            Category newRow = categoryRepository.save(newCat);
            p.setCategory(newRow);
        }

        else{
            p.setCategory(currentCategory.get());
        }

        p.setTitle(title);
        p.setDescription(description);
        p.setPrice(price);
        p.setImageUrl(image);
        Product saveProduct = productRepository.save(p);
        return saveProduct;
    }

    @Override
    public Product deleteProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, String title, String description) {
        return null;
    }
}
