package com.learn.DecProjectModule.controller;

import com.learn.DecProjectModule.models.Product;
import com.learn.DecProjectModule.service.ProductService;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    /**
     * Handles user requests
     * Connects Model and View
     * Decides what response to send
     */


    //CRUD Apis Around Product
    //For the product
    // 1. Create the product
    // 2. Get the product
    // 3. Update the product
    // 4. Delete the product

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // This will help in create product
//    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        Product p = productService.createProduct(product.getId(),product.getTitle(),
                product.getDescription(),product.getPrice(),product.getCategory().getTitle(),
                product.getImageUrl());
        return p;
    }

    // This will help in get product details
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        System.out.println(".....Starting the api here....");
        Product p = productService.getSingleProduct(id);
        System.out.println("....Ending the ap here....");
        return p;
    }

    // This will help in Update product
    public void updateProduct(Product product){

    }

    // This will help in Delete product
    public void deleteProduct(Long id){

    }
}
