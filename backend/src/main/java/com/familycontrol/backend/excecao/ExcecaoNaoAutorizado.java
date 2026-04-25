package com.familycontrol.backend.excecao;

public class ExcecaoNaoAutorizado extends RuntimeException {
    public ExcecaoNaoAutorizado(String mensagem){
        super(mensagem);
    }
}
