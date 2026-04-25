package com.familycontrol.backend.excecao;

public class ExcecaoProibido extends RuntimeException {
    public ExcecaoProibido (String mensagem){
        super(mensagem);
    }
}
