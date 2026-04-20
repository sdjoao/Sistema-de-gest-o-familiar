package com.familycontrol.backend.modelo.entidade;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "publicacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publicacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_publicacao;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String conteudo;

    @Column(name = "url_imagem", length = 255)
    private String urlImagem;

    @Column(nullable = false)
    private Integer quantidade_curtidas;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_autor", nullable = false)
    private Usuario codigoUsuario;
}
