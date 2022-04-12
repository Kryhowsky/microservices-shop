package com.kryhowsky.basket.repository;

import com.kryhowsky.basket.model.Basket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BasketRepository extends ElasticsearchRepository<Basket, String> {
}
