package com.learn.DecProjectModule.controller;

import com.learn.DecProjectModule.dto.ErrorDto;
import com.learn.DecProjectModule.exceptions.ProductNotFoundException;
import com.learn.DecProjectModule.models.Product;
import com.learn.DecProjectModule.service.ProductService;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        Product p = productService.getSingleProduct(id);

        ResponseEntity<Product> response = new ResponseEntity<>(
                p, HttpStatus.OK
        );
        return response;
    }

    // This will help in Update product
    public void updateProduct(Product product){

    }

    // This will help in Delete product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product p = productService.deleteProduct(id);
        ResponseEntity<Product> response = new ResponseEntity<>(
                p,HttpStatus.OK
        );
        return response;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(e.getMessage());

        ResponseEntity<ErrorDto> response = new ResponseEntity<>(
                errorDto,HttpStatus.NOT_FOUND
        );

        return response;
    }
}
