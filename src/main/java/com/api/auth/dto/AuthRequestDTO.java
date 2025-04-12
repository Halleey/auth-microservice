package com.api.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequestDTO {
    private String name;
    private String password;

}
