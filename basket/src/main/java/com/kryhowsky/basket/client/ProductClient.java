package com.kryhowsky.basket.client;

import com.kryhowsky.common.rest.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/{id}")
    ProductDto getProductById(@PathVariable Long id);

}
