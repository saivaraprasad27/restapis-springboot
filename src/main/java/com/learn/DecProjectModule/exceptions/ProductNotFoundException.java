package com.learn.DecProjectModule.exceptions;

public class ProductNotFoundException extends Exception{

    // Create an object of ProductNotFoundException class
    // and the error message
    public ProductNotFoundException(String message){
        super(message);
    }
}
