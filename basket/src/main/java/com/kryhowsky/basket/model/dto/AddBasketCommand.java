package com.kryhowsky.basket.model.dto;

import com.kryhowsky.common.rest.ProductDto;
import com.kryhowsky.common.rest.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBasketCommand {

    private UserDto user;
    private ProductDto product;

}
