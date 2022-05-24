package com.kryhowsky.basket.client;


import com.kryhowsky.common.rest.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/current")
    UserDto getCurrentUser(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token);

}
