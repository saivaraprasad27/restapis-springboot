package com.learn.DecProjectModule;

import com.learn.DecProjectModule.models.Category;
import com.learn.DecProjectModule.models.Product;
import com.learn.DecProjectModule.repository.CategoryRepository;
import com.learn.DecProjectModule.repository.ProductRepository;
import com.learn.DecProjectModule.repository.Projections.ProductProjection;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DecProjectModuleApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testHqlQueries(){
        List<Product> allProducts = productRepository.getProductByCategoryId(1L);
        for(Product product : allProducts){
            System.out.println(product);
        }
    }

    @Test
    void testNativeQueries(){
        List<Product> allProducts = productRepository.getProductByCategoryIdNativeQueries(1L);
        for(Product product : allProducts){
            System.out.println(product);
        }
    }

    @Test
    void testProjection(){
        List<ProductProjection> product = productRepository.getProductByCategoryIdUsingProjections(1L);
        System.out.println(product.get(0).getId());
        System.out.println(product.get(0).getTitle());
    }

    @Test
    void fetchTypeTest(){
        Category category = categoryRepository.findById(1L).get();
        System.out.println(category.getId());
        System.out.println("We are done here");

        List<Product> products = category.getProducts();
        System.out.println(products.size());

        System.out.println("We go the product as well");

    }
}
