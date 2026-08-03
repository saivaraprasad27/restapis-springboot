package com.learn.DecProjectModule.service;

import com.learn.DecProjectModule.dto.FakeStoreProductDto;
import com.learn.DecProjectModule.exceptions.ProductNotFoundException;
import com.learn.DecProjectModule.models.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service("fakeStoreProductService")
@Slf4j
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    private FakeStoreProductService(RestTemplate restTemplate, RedisTemplate redisTemplate){
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        //1. Redis Used
        //2. First part is : assume it as table name
        //3. Second part : key of the product
        log.info("Retrieving product with id {}", id);

        Product redisProduct = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+id);

        if(redisProduct != null){
            //cache hit
            return redisProduct;
        }

        FakeStoreProductDto fakeStoreProductDto = restTemplate.
                getForObject("https://fakestoreapi.com/products/"+ id, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null){
            throw new ProductNotFoundException("Product Not Found with id: "+id);
        }

        //cache miss and Delete the Redis key PRODUCTS automatically after 10 minutes
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCTS_"+id,fakeStoreProductDto.getProduct());
        redisTemplate.expire("PRODUCTS", 10, TimeUnit.MINUTES);

        return fakeStoreProductDto.getProduct();
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String fieldName) {
        return null;
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

    @Override
    public Page<Product> getAllProductsList(int limit, int offset, String fieldName) {
        return null;
    }
}
