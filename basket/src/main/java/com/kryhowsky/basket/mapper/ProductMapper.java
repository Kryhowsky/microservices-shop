package com.kryhowsky.basket.mapper;

import com.kryhowsky.basket.model.Product;
import com.kryhowsky.common.rest.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToDto(Product product);

    Product productDtoToDao(ProductDto productDto);

    List<ProductDto> productListToProductDtoList(List<Product> products);

}
