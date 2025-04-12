package com.api.auth.jwt;

import com.api.auth.dto.DoctorResponseDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtil {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expirationMs;

    private Key generateKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Date toExpireDate() {
        return new Date(System.currentTimeMillis() + expirationMs);
    }

    public Mono<Token> generateToken(DoctorResponseDTO user) {
        return Mono.fromCallable(() -> {
            String token = Jwts.builder()
                    .setHeaderParam("typ", "JWT")
                    .setSubject(user.getName())
                    .claim("name", user.getName())
                    .claim("crm", user.getCrm())
                    .claim("expertise", user.getExpertise())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(toExpireDate())
                    .signWith(generateKey(), SignatureAlgorithm.HS256)
                    .compact();
            System.out.println("✅ Token gerado com sucesso: " + token);
            return new Token(token);
        }).subscribeOn(Schedulers.boundedElastic());
    }


    private Claims getClaimsFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(refactorToken(token))
                    .getBody();

            System.out.println("🔍 Claims decodificados: " + claims);
            return claims;
        } catch (JwtException ex) {
            System.err.println("❌ Erro ao decodificar token: " + ex.getMessage());
            return null;
        }
    }

    public Mono<Boolean> isTokenValid(String token) {
        return Mono.fromCallable(() -> {
            try {
                Jwts.parser()
                        .setSigningKey(generateKey())
                        .build()
                        .parseClaimsJws(refactorToken(token));

                System.out.println("✅ Token válido: " + token);
                return true;
            } catch (JwtException ex) {
                System.err.println("❌ Token inválido: " + ex.getMessage());
                return false;
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<String> getUsernameFromToken(String token) {
        return Mono.fromCallable(() -> getClaimsFromToken(token))
                .map(Claims::getSubject)
                .subscribeOn(Schedulers.boundedElastic());
    }
    private String refactorToken(String token) {
        return token.replaceFirst("^Bearer\\s*", "");
    }
}
