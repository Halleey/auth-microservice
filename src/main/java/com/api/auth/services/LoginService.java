package com.api.auth.services;

import com.api.auth.dto.AuthRequestDTO;
import reactor.core.publisher.Mono;

public interface LoginService {
    Mono<String> loginAuthenticate(AuthRequestDTO requestDTO);

    Mono<String> loginNurse(AuthRequestDTO requestDTO);
}
