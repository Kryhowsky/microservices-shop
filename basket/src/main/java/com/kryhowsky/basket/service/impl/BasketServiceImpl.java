package com.kryhowsky.basket.service.impl;

import com.kryhowsky.basket.client.ProductClient;
import com.kryhowsky.basket.client.UserClient;
import com.kryhowsky.basket.exception.QuantityExceededException;
import com.kryhowsky.basket.mapper.ProductMapper;
import com.kryhowsky.basket.model.Basket;
import com.kryhowsky.basket.model.Product;
import com.kryhowsky.basket.model.dto.AddBasketCommand;
import com.kryhowsky.basket.model.dto.BasketDto;
import com.kryhowsky.basket.repository.BasketRepository;
import com.kryhowsky.basket.service.BasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public record BasketServiceImpl(ProductClient productClient, RestTemplate restTemplate,
                                BasketRepository basketRepository, UserClient userClient,
                                ProductMapper productMapper) implements BasketService {

    @Override
    public void addProductToBasket(Long productId, Integer quantity, String token) throws ExecutionException, InterruptedException {

        System.out.println("SERVICE");

        var addBasket = CompletableFuture.supplyAsync(() -> userClient.getCurrentUser(token))
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> productClient.getProductById(productId)), AddBasketCommand::new)
                .get();

        System.out.println("BASKET SERVICE");

        var currentUserLogin = addBasket.getUser().getLogin();

        var basket = basketRepository.findByLogin(currentUserLogin).orElseGet(() -> {
            var newBasket = new Basket();
            newBasket.setLogin(currentUserLogin);
            newBasket.setProducts(new ArrayList<>());
            return newBasket;
        });

        var productDto = addBasket.getProduct();
        var product = productMapper.productDtoToDao(productDto);

        if (productDto.getQuantity() >= quantity) {
            product.setQuantity(quantity);
        } else {
            throw new QuantityExceededException("Quantity exceeded");
        }

        basket.getProducts().add(product);

        System.out.println("BASKET SERVICE-SAVE");

        basketRepository.save(basket);
    }

    @Override
    public List<Product> getProductsByCurrentUser() {
        return basketRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName())
                .map(Basket::getProducts)
                .orElse(Collections.emptyList());
    }

    @Override
    public void deleteProductFromCurrentUserBasket(Long id) {

        var basketDb = basketRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();

        basketDb.getProducts().removeIf(product -> product.getId().equals(id));

        basketRepository.save(basketDb);

    }

    @Override
    public void clearBasket() {

        var basketDb = basketRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        basketDb.setProducts(Collections.emptyList());

        basketRepository.save(basketDb);

    }

    @Override
    public Basket updateCurrentUserBasket(BasketDto basketDto) {

        var productDb = productClient.getProductById(basketDto.getProductId());

        if (productDb.getQuantity() >= basketDto.getQuantity()) {
            var basketDb = basketRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(); // TODO: exception + adviceController
            basketDb.getProducts().stream() // TODO: jeśli istnieje to zmieniam quantity, jeśli nie to dodaje do koszyka
                    .filter(product -> product.getId().equals(basketDto.getProductId()))
                    .findFirst()
                    .get()// ifPresentOrElse
                    .setQuantity(basketDto.getQuantity());
            return basketRepository.save(basketDb);
        }

        return null; // TODO: ?
    }
}
