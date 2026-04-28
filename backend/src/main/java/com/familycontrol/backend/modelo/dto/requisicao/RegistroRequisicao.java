package com.familycontrol.backend.modelo.dto.requisicao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistroRequisicao(
    
    @NotBlank(message = "Nome é obrigatório")
    String nome,

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    String email,

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve conter mais de 6 caracteres")
    String senha,

    @NotBlank(message = "Nome da familia é obrigatório")
    String nomeFamilia,

    @NotNull(message = "Código familia inválido, campo é obrigatório")
    Long codigoFamilia
){}
