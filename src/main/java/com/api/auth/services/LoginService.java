package com.api.auth.services;

import com.api.auth.dto.AuthRequestDTO;

public interface LoginService {

    public String loginAuthenticate(AuthRequestDTO requestDTO);

}
