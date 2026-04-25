package com.familycontrol.backend.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.familycontrol.backend.modelo.entidade.Familia;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {
    Optional<Familia> findbyCodigoFamilia(String codigoFamilia);

    boolean existisByCodigoFamilia(String codigoFamilia); 
}
