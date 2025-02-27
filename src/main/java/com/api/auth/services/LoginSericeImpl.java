package com.api.auth.services;

import com.api.auth.dto.AuthRequestDTO;
import com.api.auth.dto.DoctorResponseDTO;
import com.api.auth.jwt.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LoginSericeImpl implements LoginService {

    private final WebClient webClient;
    private final JwtUtil jwtUtil;

    public LoginSericeImpl(WebClient webClient, JwtUtil jwtUtil) {
        this.webClient = webClient;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String loginAuthenticate(AuthRequestDTO requestDTO) {
        DoctorResponseDTO doctorResponseDTO = webClient.get().uri(uriBuilder ->
                uriBuilder.path("/doctors").queryParam("name", requestDTO.getName())
                        .queryParam("password", requestDTO.getPassword())
                        .build()).retrieve().bodyToMono(DoctorResponseDTO.class).block();


        // Se o retorno for nulo, significa que o usuário não existe
        if (doctorResponseDTO == null) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(doctorResponseDTO.getName(), doctorResponseDTO.getCrm(), doctorResponseDTO.getExpertise());
    }

}
