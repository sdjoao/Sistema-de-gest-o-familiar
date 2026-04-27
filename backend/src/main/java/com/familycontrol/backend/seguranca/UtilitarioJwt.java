package com.familycontrol.backend.seguranca;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component //framework vai controlar sozinho essa classe
public class UtilitarioJwt {
    
    @Value("${jwt.segredo}")
    private String segredo;

    @Value("${jwt.expiracao}")
    private Long expiracao;

    private SecretKey gerarChave(){
        return Keys.hmacShaKeyFor(segredo.getBytes());
    }

    public String gerarToken(String email, Long codigoFamilia, String perfil){
        return Jwts.builder()
                .subject(email) // identificador
                .claim("codigoFamilia", codigoFamilia) // dados que vamos capturar via token
                .claim("perfil", perfil) // dados que vamos capturar via token
                .issuedAt(new Date()) // quando foi gerado
                .expiration(new Date(System.currentTimeMillis() + expiracao)) // quando expira
                .signWith(gerarChave()) // assinar com a chave que é gerada
                .compact(); 
    }

    public String extrarEmail(String token){
        return extrairClaims(token).getSubject();
    }

    public Long extrarCodigoFamilia(String token){
        return extrairClaims(token).get("codigoFamilia", Long.class);
    }

    public String extrairPerfil(String token){
        return extrairClaims(token).get("perfil", String.class);
    }

    public boolean tokenValido(String token){
        try {
            extrairClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims extrairClaims(String token){
        return Jwts.parser()
                .verifyWith(gerarChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
