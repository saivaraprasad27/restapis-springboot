package com.learn.DecProjectModule.repository;

import com.learn.DecProjectModule.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //save

    //get

    //get all

    //delete

    //update


    // This will insert my product records in my product table
    Product save(Product product);

    //Create query like "SELECT * from product Where description = "description"
    //Hibernate will do this
    Product findByDescription(String description);

    //"SELECT * from product Where title = "title"
    Product findByTitle(String description);

}
