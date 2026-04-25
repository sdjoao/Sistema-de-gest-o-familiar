package com.familycontrol.backend.modelo.dto.resposta;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.familycontrol.backend.modelo.entidade.Membro;
import com.familycontrol.backend.modelo.enumeracao.TipoParentesco;

public record MembroResposta(
    Long codigoMembro,
    String nome,
    LocalDate dataNascimento,
    TipoParentesco parentesco,
    String telefone,
    String urlFoto,
    Boolean ativo,
    LocalDateTime criadoEm,
    LocalDateTime atualizadoEm
){
    // método que converte ENTIDADE -> DTO
    public static MembroResposta deEntidade(Membro membro){
        return new MembroResposta(
            membro.getCodigo_membro(),
            membro.getNome(),
            membro.getDataNascimento(),
            membro.getTipoParentesco(),
            membro.getTelefone(),
            membro.getUrlFoto(),
            membro.getAtivo(),
            membro.getCriadoEm(),
            membro.getAtualizadoEm()
        );
    }
}
