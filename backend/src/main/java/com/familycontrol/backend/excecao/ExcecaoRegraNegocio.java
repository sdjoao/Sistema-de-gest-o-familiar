package com.familycontrol.backend.excecao;

public class ExcecaoRegraNegocio extends RuntimeException {
    public ExcecaoRegraNegocio(String mensagem){
        super(mensagem);
    }
}
