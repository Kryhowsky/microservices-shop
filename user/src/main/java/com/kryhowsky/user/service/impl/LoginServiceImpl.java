package com.kryhowsky.user.service.impl;

import com.kryhowsky.user.model.dto.LoginDto;
import com.kryhowsky.user.model.dto.TokenDto;
import com.kryhowsky.user.repository.UserRepository;
import com.kryhowsky.user.service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    @Override
    public TokenDto authenticateUser(LoginDto loginDto) {

        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getLogin(), loginDto.getPassword());

        try {
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            var claims = new DefaultClaims()
                    .setSubject(authenticate.getName())
                    .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
            claims.put("authorities", authenticate.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(",")));

            return TokenDto.builder()
                    .token(Jwts.builder()
                            .setClaims(claims)
                            .signWith(SignatureAlgorithm.HS512, "akdsbgkasdbixzvzov345hkjefbgmvx")
                            .compact())
                    .build();
        } catch (Exception e) {
            throw new AuthenticationServiceException("Error during login.", e);
        }

    }
}
