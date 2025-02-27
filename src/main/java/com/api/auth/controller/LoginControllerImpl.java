package com.api.auth.controller;

import com.api.auth.dto.AuthRequestDTO;
import com.api.auth.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginControllerImpl implements LoginFace {

    private final LoginService loginService;

    public LoginControllerImpl(LoginService loginService) {
        this.loginService = loginService;
    }
    @Override
    public ResponseEntity<String> loginController(AuthRequestDTO requestDTO) {
        String token = loginService.loginAuthenticate(requestDTO);
        return ResponseEntity.ok(token);
    }
}
