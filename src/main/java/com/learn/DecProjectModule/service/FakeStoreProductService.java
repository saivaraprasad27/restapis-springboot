package com.learn.DecProjectModule.service;

import com.learn.DecProjectModule.dto.FakeStoreProductDto;
import com.learn.DecProjectModule.exceptions.ProductNotFoundException;
import com.learn.DecProjectModule.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    private FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.
                getForObject("https://fakestoreapi.com/products/"+ id, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product Not Found with id: "+id);
        }
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


/*       Client
          |
          |  DELETE /products/10
          v
     Controller (@DeleteMapping)
          |
          |  calls service
          v
    Service (RestTemplate)
          |
          |  DELETE https://fakestoreapi.com/products/10
          v
    FakeStore API

 */

    public Product deleteProduct(Long id){
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.exchange(
                        "https://fakestoreapi.com/products/" + id,
                        HttpMethod.DELETE,
                        null,
                        FakeStoreProductDto.class
                );
        return response.getBody().getProduct();
    }

    public Product updateProduct(Long id, String title,String description){
        return null;
    }
}
