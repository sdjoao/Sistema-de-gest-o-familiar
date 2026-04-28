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
    
    List<Despesa> findByFamiliaCodigoFamiliaOrderByDataReceitaDesc(Long codigoFamilia);

    List<Despesa> findByFamiliaCodigoFamiliaAndDataReceitaBetweenOrderByDataReceitaDesc(
        Long CodigoFamilia,
        LocalDate dataInicio,
        LocalDate dataFim
    );

    @Query("SELECT COALESCE(SUM(r.valor), 0) FROM Receita r " +
            "WHERE r.familia.codigoFamilia = :codigoFamilia " +
            "AND MONTH(r.dataReceita) = :mes " +
            "AND YEAR (r.dataReceita) = :ano ")
    BigDecimal somarPorMesEAno(
        @Param("codigoFamilia") Long codigoFamilia,
        @Param("mes") int mes,
        @Param("ano") int ano
    );
}
