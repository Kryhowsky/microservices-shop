package com.kryhowsky.basket.service;

import com.kryhowsky.basket.model.Basket;
import com.kryhowsky.basket.model.Product;
import com.kryhowsky.basket.model.dto.BasketDto;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface BasketService {

    void addProductToBasket(Long productId, Integer quantity, String token) throws ExecutionException, InterruptedException;
    List<Product> getProductsByCurrentUser();

    void deleteProductFromCurrentUserBasket(Long id);
    void clearBasket();

    Basket updateCurrentUserBasket(BasketDto basketDto);

}
