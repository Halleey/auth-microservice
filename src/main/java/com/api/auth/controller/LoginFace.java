package com.api.auth.controller;

import com.api.auth.dto.AuthRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginFace {

    @PostMapping
    public ResponseEntity<String> loginController(@RequestBody AuthRequestDTO requestDTO);
}
