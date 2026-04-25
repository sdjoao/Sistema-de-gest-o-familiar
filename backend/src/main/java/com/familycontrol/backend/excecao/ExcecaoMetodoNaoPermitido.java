package com.familycontrol.backend.excecao;

public class ExcecaoMetodoNaoPermitido extends RuntimeException {
    public ExcecaoMetodoNaoPermitido(String mensagem){
        super(mensagem);
    }
}
