package com.familycontrol.backend.modelo.entidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.familycontrol.backend.modelo.enumeracao.CategoriaDespesa;

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
@Table(name = "despesas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Despesa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo_despesa;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Column(name = "data_despesa", nullable = false)
    private LocalDate dataDespesa;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private CategoriaDespesa categoriaDespesa;

    @Column(length = 255)
    private String observacao;

    @Column(nullable = false)
    private Boolean pago = false;

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
    private Familia codigoFamilia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_usuario_criacao", nullable = false)
    private Usuario codigoUsuarioCriacao;


}
