package com.kryhowsky.user.service;

import com.kryhowsky.user.model.dto.LoginDto;
import com.kryhowsky.user.model.dto.TokenDto;

public interface LoginService {

    TokenDto authenticateUser(LoginDto loginDto);

}