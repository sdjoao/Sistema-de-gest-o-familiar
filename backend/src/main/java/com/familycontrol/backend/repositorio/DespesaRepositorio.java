package com.familycontrol.backend.repositorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.familycontrol.backend.modelo.entidade.Despesa;

@Repository
public interface DespesaRepositorio extends JpaRepository<Despesa, Long> {
    
    List<Despesa> findByCodigoFamiliaOrderByDataDespesaDesc(Long codigoFamilia);

    List<Despesa> findByCodigoFamiliaAndDataDespesaBetweenOrderByDataDespesaDesc(
        Long CodigoFamilia,
        LocalDate dataInicio,
        LocalDate dataFim
    );

    @Query("SELECT COALESCE(SUM(d.valor), 0 FROM Despesa d " +
            "WHERE d.codigo_familia = :codigo_familia " +
            "AND MONTH(d.data_despesa) = :mes " +
            "AND YEAR (d.data_despesa = :ano ")
    BigDecimal somarPorMesEAno(
        @Param("codigoFamilia") Long codigoFamilia,
        @Param("mes") int mes,
        @Param("ano") int ano
    );
}
