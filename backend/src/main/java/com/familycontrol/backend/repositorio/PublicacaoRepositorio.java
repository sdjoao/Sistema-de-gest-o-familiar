package com.familycontrol.backend.repositorio;

import org.springframework.stereotype.Repository;

import com.familycontrol.backend.modelo.entidade.Publicacao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PublicacaoRepositorio extends JpaRepository<Publicacao, Long> {
    
    List<Publicacao> findByFamiliaCodigoFamiliaOrderByCriadoEmDesc(Long codigoFamilia);
}
