package com.ufrn.pw.core.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    private final Key chave = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiracao = 7200000; // 2 horas

    public String gerarToken(String username) {
        Date agora = new Date();
        Date dataExpiracao = new Date(agora.getTime() + expiracao);

        return Jwts.builder()
                .setIssuer("API Biblioteca UFRN")
                .setSubject(username)
                .setIssuedAt(agora)
                .setExpiration(dataExpiracao)
                .signWith(chave)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(chave).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(chave).build().parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}