package com.familycontrol.backend.repositorio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.familycontrol.backend.modelo.entidade.Despesa;
import com.familycontrol.backend.modelo.entidade.Receita;

@Repository
public interface ReceitaRepositorio extends JpaRepository<Receita, Long>{
    
    List<Despesa> findByCodigoFamiliaOrderByDataRespesaDesc(Long codigoFamilia);

    List<Despesa> findByCodigoFamiliaAndDataRespesaBetweenOrderByDataDespesaDesc(
        Long CodigoFamilia,
        LocalDate dataInicio,
        LocalDate dataFim
    );

    @Query("SELECT COALESCE(SUM(d.valor), 0 FROM Receita r " +
            "WHERE r.codigo_familia = :codigo_familia " +
            "AND MONTH(r.data_receita) = :mes " +
            "AND YEAR (r.data_receita = :ano ")
    BigDecimal somarPorMesEAno(
        @Param("codigoFamilia") Long codigoFamilia,
        @Param("mes") int mes,
        @Param("ano") int ano
    );
}
