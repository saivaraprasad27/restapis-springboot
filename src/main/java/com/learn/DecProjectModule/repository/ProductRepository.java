package com.learn.DecProjectModule.repository;

import com.learn.DecProjectModule.models.Product;
import com.learn.DecProjectModule.repository.Projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    //Implement HQL
    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> getProductByCategoryId(@Param("categoryId") Long categoryId);

    // Native Queries
    @Query(value = "select * from product p where p.category_id = :categoryId", nativeQuery = true)
    List<Product> getProductByCategoryIdNativeQueries(@Param("categoryId") Long categoryId);

    //Projections
    @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
    List<ProductProjection> getProductByCategoryIdUsingProjections(@Param("categoryId") Long categoryId);

}
