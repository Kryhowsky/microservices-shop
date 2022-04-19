package com.kryhowsky.product.mapper;

import com.kryhowsky.common.rest.ProductDto;
import com.kryhowsky.product.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);

    Product toDao(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);

}
