package com.kryhowsky.product.controller;

import com.kryhowsky.product.model.Product;
import com.kryhowsky.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(description = "Allows to add new Product")
    public Product saveProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns Product with specific Id.")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    @Operation(description = "Returns page of Products with specific size.")
    public Page<Product> getProductPage(@RequestParam int page, @RequestParam int size) {
        return productService.getPage(PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    @Operation(description = "Allows to update the Product with specific Id.")
    public Product updateProduct(@RequestBody Product product, @PathVariable Long id) {
        return productService.update(product, id);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Allows to delete Product with specific Id.")
    public void deleteProductById(@PathVariable Long id) {
        productService.delete(id);
    }
}
