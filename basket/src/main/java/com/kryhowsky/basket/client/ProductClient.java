package com.kryhowsky.basket.client;

import com.kryhowsky.common.rest.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/{id}")
//    @Retry(name = "productApi", fallbackMethod = "defaultMethod")
//    @CircuitBreaker(name = "productApi", fallbackMethod = "defaultMethod")
    ProductDto getProductById(@PathVariable Long id);

    default ProductDto defaultMethod(Exception exception) {
        System.out.println("DEFAULT METHOD");
        return ProductDto.builder()
                .quantity(-1)
                .build();
    }

}
