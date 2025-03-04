package com.api.auth.services;

import com.api.auth.dto.AuthRequestDTO;
import com.api.auth.dto.DoctorResponseDTO;
import com.api.auth.jwt.JwtUtil;
import com.api.auth.jwt.Token;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class LoginSericeImpl implements LoginService {

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    public LoginSericeImpl(WebClient webClient, JwtUtil jwtUtil) {
        this.webClient = webClient;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Mono<String> loginAuthenticate(AuthRequestDTO requestDTO) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/doctors")
                        .queryParam("name", requestDTO.getName())
                        .queryParam("password", requestDTO.getPassword())
                        .build())
                .retrieve()
                .bodyToMono(DoctorResponseDTO.class)
                .flatMap(doctorResponseDTO -> {
                    if (doctorResponseDTO == null) {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }
                    return jwtUtil.generateToken(doctorResponseDTO)
                            .map(Token::token); // Extrai apenas a string do token
                });
    }
    }

