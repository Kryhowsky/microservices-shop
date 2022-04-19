package com.kryhowsky.user.controller;

import com.kryhowsky.user.model.dto.LoginDto;
import com.kryhowsky.user.model.dto.TokenDto;
import com.kryhowsky.user.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login", produces = MediaType.APPLICATION_JSON_VALUE)
public record LoginController (LoginService loginService) {

    @PostMapping
    @Operation(description = "Allows to login.")
    public TokenDto authenticateUser(@RequestBody LoginDto loginDto) {

        return loginService.authenticateUser(loginDto);

    }

}