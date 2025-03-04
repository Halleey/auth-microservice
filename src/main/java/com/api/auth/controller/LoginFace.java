package com.api.auth.controller;

import com.api.auth.dto.AuthRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface LoginFace {

    @PostMapping
    Mono<ResponseEntity<String>> loginController(@RequestBody AuthRequestDTO requestDTO);
}
