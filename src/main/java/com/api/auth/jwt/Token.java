package com.api.auth.jwt;

public class Token {
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String token() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
