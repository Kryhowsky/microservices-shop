package com.kryhowsky.product.service;

import com.kryhowsky.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product save(Product product);
    Product update(Product product, Long id);
    void delete(Long id);
    Product getProductById(Long id);
    Page<Product> getPage(Pageable pageable);

}
