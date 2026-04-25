package com.familycontrol.backend.excecao;

public class ExcecaoNaoEncontrado extends RuntimeException {
    public ExcecaoNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
