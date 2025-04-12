package com.api.auth.controller;

import com.api.auth.dto.AuthRequestDTO;
import com.api.auth.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/login")
public class LoginControllerImpl implements LoginFace {

    private final LoginService loginService;

    public LoginControllerImpl(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Mono<ResponseEntity<String>> loginController(AuthRequestDTO requestDTO) {
        return loginService.loginAuthenticate(requestDTO)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()))); // Trata erro
    }
}

