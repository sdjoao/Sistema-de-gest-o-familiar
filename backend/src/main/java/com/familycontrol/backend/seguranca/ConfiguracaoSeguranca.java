package com.familycontrol.backend.seguranca;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracaoSeguranca {
    
    private final FiltroJwt filtroJwt;

    @Bean
    public SecurityFilterChain filtroDeSeguranca(HttpSecurity http) throws Exception{
        http
            // Desabilita CSRF
            .csrf(AbstractHttpConfigurer::disable)
            // Definindo CORS
            .cors(cors -> cors.configurationSource(configuracaoCors()))
            // Definindo rotas que não precisam de autenticacao
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/autenticacao/**").permitAll()
                .anyRequest().authenticated()
            )
            // Faz cada requisição ser independente e usar o token
            .sessionManagement(sessao -> sessao
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            // Adiciona o filtro criado manualmente no lugar do filtro do spring 
            .addFilterBefore(filtroJwt, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource configuracaoCors(){
        CorsConfiguration configuracao = new CorsConfiguration();
        configuracao.setAllowedOrigins(List.of(
            "http://localhost:5173",  // Vite
            "http://localhost:3000"   // React App
        ));
        configuracao.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS"
        ));
        configuracao.setAllowedHeaders(List.of("*"));
        configuracao.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource fonte = new UrlBasedCorsConfigurationSource();
        fonte.registerCorsConfiguration("/api/**", configuracao);
        return fonte;
    }

    // algoritmo de hash
    @Bean
    public PasswordEncoder codificarSenha(){
        return new BCryptPasswordEncoder();
    }

    // gerenciador de autenticacao - usado no login
    @Bean
    public AuthenticationManager gerenciarAutenticacao(AuthenticationConfiguration configuracao) throws Exception{
        return configuracao.getAuthenticationManager();
    }
}
