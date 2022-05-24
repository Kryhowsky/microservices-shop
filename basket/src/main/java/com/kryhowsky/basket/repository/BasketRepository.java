package com.kryhowsky.basket.repository;

import com.kryhowsky.basket.model.Basket;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface BasketRepository extends ElasticsearchRepository<Basket, String> {

    Optional<Basket> findByLogin(String login);

}
