package com.familycontrol.backend.modelo.dto.resposta;

public record LoginResposta(
    String token,
    String nome,
    String email,
    String perfil,
    Long codigoFamilia,
    String nomeFamilia
){}
