package com.familycontrol.backend.modelo.dto.requisicao;

import java.time.LocalDate;

import com.familycontrol.backend.modelo.enumeracao.TipoParentesco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MembroRequisicao(

    @NotBlank(message = "É necessário preencher o nome para o membro da familia")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 ou 100 caracteres")
    String nome,
    
    LocalDate dataNascimento,
    TipoParentesco parentesco,

    @Size(max = 20, message = "O telefone não pode possuir mais de 20 caracteres")
    String telefone,

    String urlFoto
){}
