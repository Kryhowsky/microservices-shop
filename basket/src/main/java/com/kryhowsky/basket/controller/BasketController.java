package com.kryhowsky.basket.controller;

import com.kryhowsky.basket.mapper.ProductMapper;
import com.kryhowsky.basket.model.Basket;
import com.kryhowsky.basket.model.dto.BasketDto;
import com.kryhowsky.basket.repository.BasketRepository;
import com.kryhowsky.basket.service.BasketService;
import com.kryhowsky.common.rest.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/baskets")
public record BasketController (BasketRepository basketRepository, BasketService basketService, ProductMapper productMapper) {

    @GetMapping
    @Operation(description = "Allows to get Products for current user.")
    public List<ProductDto> getProductsForCurrentUser() {
        return productMapper.productListToProductDtoList(basketService.getProductsByCurrentUser());
    }

    @DeleteMapping
    @Operation(description = "Allows to clear Basket for current user.")
    public void clearBasket() {
        basketService.clearBasket();
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Product specified by Id from current user basket.")
    public void deleteProductById(@PathVariable Long id) {
        basketService.deleteProductFromCurrentUserBasket(id);
    }

    @PutMapping
    @Operation(description = "Allows to update Basket for current user.")
    public Basket updateBasket(@RequestBody BasketDto basketDto) {
        return basketService.updateCurrentUserBasket(basketDto);
    }

    @PostMapping
    public void addProductToBasket(@RequestBody @Valid BasketDto basketDto, @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) throws ExecutionException, InterruptedException {
        basketService.addProductToBasket(basketDto.getProductId(), basketDto.getQuantity(), token);
    }

}
