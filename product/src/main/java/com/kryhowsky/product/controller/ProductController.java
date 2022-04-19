package com.kryhowsky.product.controller;

import com.kryhowsky.common.rest.ProductDto;
import com.kryhowsky.product.mapper.ProductMapper;
import com.kryhowsky.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public record ProductController (ProductMapper productMapper, ProductService productService) {

    @PostMapping
    @Operation(description = "Allows to add new Product")
    public ProductDto saveProduct(@RequestBody @Valid ProductDto product) {
        return productMapper.toDto(productService.save(productMapper.toDao(product)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Product with specific Id.")
    public ProductDto getProductById(@PathVariable Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @GetMapping
    @Operation(description = "Returns page of Products with specific size.")
    public Page<ProductDto> getProductPage(@RequestParam int page, @RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size)).map(productMapper::toDto);
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update the Product with specific Id.")
    public ProductDto updateProduct(@RequestBody @Valid ProductDto product, @PathVariable Long id) {
        return productMapper.toDto(productService.update(productMapper.toDao(product), id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Product with specific Id.")
    public void deleteProductById(@PathVariable Long id) {
        productService.delete(id);
    }
}
