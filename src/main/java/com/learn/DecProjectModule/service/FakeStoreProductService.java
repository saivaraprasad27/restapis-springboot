package com.learn.DecProjectModule.service;

import com.learn.DecProjectModule.dto.FakeStoreProductDto;
import com.learn.DecProjectModule.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    private FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(Long id) {
        System.out.println("Inside FK Product Service");
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+ id, FakeStoreProductDto.class);
        System.out.println(fakeStoreProductDto.toString());
        return fakeStoreProductDto.getProduct();
    }

    public List<Product> getAllProducts() {
        return List.of();
    }

    public Product createProduct(Long id, String title, String description, double price, String category, String image) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto,FakeStoreProductDto.class);
        return response.getProduct();
    }
}
