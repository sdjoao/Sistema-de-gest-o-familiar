package com.familycontrol.backend.excecao;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ManipularGlobalExcecoes {
    
    @ExceptionHandler(ExcecaoRegraNegocio.class)
    public ResponseEntity<ExcecaoResposta> requisicaoInvalida(ExcecaoRegraNegocio excecao, HttpServletRequest requisicao){
        
        ExcecaoResposta erro = new ExcecaoResposta(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            excecao.getMessage(),
            requisicao.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(erro);
    }

    @ExceptionHandler(ExcecaoNaoAutorizado.class)
    public ResponseEntity<ExcecaoResposta> naoAutorizado(ExcecaoRegraNegocio excecao, HttpServletRequest requisicao){
        
        ExcecaoResposta erro = new ExcecaoResposta(
            LocalDateTime.now(),
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            excecao.getMessage(),
            requisicao.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(erro);
    }

    @ExceptionHandler(ExcecaoProibido.class)
    public ResponseEntity<ExcecaoResposta> proibido(ExcecaoRegraNegocio excecao, HttpServletRequest requisicao){
        
        ExcecaoResposta erro = new ExcecaoResposta(
            LocalDateTime.now(),
            HttpStatus.FORBIDDEN.value(),
            HttpStatus.FORBIDDEN.getReasonPhrase(),
            excecao.getMessage(),
            requisicao.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.FORBIDDEN)
            .body(erro);
    }

    @ExceptionHandler(ExcecaoNaoEncontrado.class)
    public ResponseEntity<ExcecaoResposta> naoEncontrado(ExcecaoRegraNegocio excecao, HttpServletRequest requisicao){
        
        ExcecaoResposta erro = new ExcecaoResposta(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            excecao.getMessage(),
            requisicao.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(erro);
    }

    @ExceptionHandler(ExcecaoMetodoNaoPermitido.class)
    public ResponseEntity<ExcecaoResposta> metodoNaoPermitido(ExcecaoRegraNegocio excecao, HttpServletRequest requisicao){
        
        ExcecaoResposta erro = new ExcecaoResposta(
            LocalDateTime.now(),
            HttpStatus.METHOD_NOT_ALLOWED.value(),
            HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
            excecao.getMessage(),
            requisicao.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.METHOD_NOT_ALLOWED)
            .body(erro);
    }

    @ExceptionHandler(ExcecaoServicoIndisponivel.class)
    public ResponseEntity<ExcecaoResposta> servicoIndisponivel(ExcecaoRegraNegocio excecao, HttpServletRequest requisicao){
        
        ExcecaoResposta erro = new ExcecaoResposta(
            LocalDateTime.now(),
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            excecao.getMessage(),
            requisicao.getRequestURI()
        );

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(erro);
    }
}
