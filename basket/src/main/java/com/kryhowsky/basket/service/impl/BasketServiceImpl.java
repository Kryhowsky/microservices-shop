package com.kryhowsky.basket.service.impl;

import com.kryhowsky.basket.client.ProductClient;
import com.kryhowsky.basket.service.BasketService;
import com.kryhowsky.common.rest.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public record BasketServiceImpl (ProductClient productClient, RestTemplate restTemplate) implements BasketService {

    @Override
    public void addProductToBasket(Long id) {
        log.info("{}", restTemplate.getForObject("http://PRODUCT-SERVICE/" + id, ProductDto.class));
        log.info("{}", productClient.getProductById(id));
    }

}
