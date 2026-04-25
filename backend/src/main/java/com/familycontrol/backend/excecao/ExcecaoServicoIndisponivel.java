package com.familycontrol.backend.excecao;

public class ExcecaoServicoIndisponivel extends RuntimeException {
    public ExcecaoServicoIndisponivel(String mensagem){
        super(mensagem);
    }
}
