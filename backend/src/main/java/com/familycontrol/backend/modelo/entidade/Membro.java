package com.familycontrol.backend.modelo.entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.familycontrol.backend.modelo.enumeracao.TipoParentesco;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "membros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_membro;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, name = "tipo_parentesco")
    private TipoParentesco tipoParentesco;

    @Column(length = 20)
    private String telefone;

    @Column(name = "url_foto", length = 255)
    private String urlFoto;

    @Column(nullable = false)
    private Boolean ativo = true;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_familia", nullable = false)
    private Familia familia;
}
