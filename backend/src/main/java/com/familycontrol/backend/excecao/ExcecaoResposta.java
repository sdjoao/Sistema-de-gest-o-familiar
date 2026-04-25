package com.familycontrol.backend.excecao;

import java.time.LocalDateTime;

public record ExcecaoResposta(
    LocalDateTime horario,
    int status,
    String tipoDeErro,
    String mensagem,
    String caminhoDaUrl
){}
