package com.kryhowsky.basket.controller;

import com.kryhowsky.basket.model.Basket;
import com.kryhowsky.basket.repository.BasketRepository;
import com.kryhowsky.basket.service.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public record BasketController (BasketRepository basketRepository, BasketService basketService) {

    @GetMapping("/{id}")
    @Operation(description = "Allows to get Basket by Id.")
    public Basket getBasket(@PathVariable String id) {
        return basketRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Basket specified by Id.")
    public void deleteBasketById(@PathVariable String id) {
        basketRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update Basket specified by Id.")
    public Basket updateBasket(@RequestBody Basket basket, @PathVariable String id) {
        return basketRepository.save(basket);
    }

    @GetMapping
    @Operation(description = "Returns page of Baskets with specific size")
    public Page<Basket> getBasketPage(@RequestParam int page, @RequestParam int size) {
        return basketRepository.findAll(PageRequest.of(page, size));
    }

    @PostMapping
    public void addProductToBasket(@RequestParam Long id) {
        basketService.addProductToBasket(id);
    }

}
