package com.familycontrol.backend.seguranca;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FiltroJwt extends OncePerRequestFilter {
    
    private final UtilitarioJwt utilitarioJwt;

    @Override
    protected void doFilterInternal(
        HttpServletRequest requisicao,
        HttpServletResponse resposta, 
        FilterChain cadeia)
        throws ServletException, IOException{

        String cabecalho = requisicao.getHeader("Authorization");
        if(cabecalho != null && cabecalho.startsWith("Bearer ")){
            
            String token = cabecalho.substring(7);
            if(utilitarioJwt.tokenValido(token)){
                String email = utilitarioJwt.extrarEmail(token);
                String perfil = utilitarioJwt.extrairPerfil(token);
                Long codigoFamilia = utilitarioJwt.extrarCodigoFamilia(token);

                UsernamePasswordAuthenticationToken autenticacao = 
                    new UsernamePasswordAuthenticationToken(
                        email,
                        codigoFamilia,
                        List.of(new SimpleGrantedAuthority("ROLE_" + perfil))
                    );
                
                SecurityContextHolder.getContext()
                    .setAuthentication(autenticacao);
            }
        }
        cadeia.doFilter(requisicao, resposta);
    }
}
