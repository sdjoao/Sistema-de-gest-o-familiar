package com.familycontrol.backend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.familycontrol.backend.modelo.entidade.Membro;

@Repository
public interface MembroRepositorio extends JpaRepository<Membro, Long>{
    
    List<Membro> findByCodigoFamiliaOrderByNome(Long codigoFamilia);

    boolean existisByNomeAndCodigoFamilia(String nome, Long codigoFamilia);

    
}
