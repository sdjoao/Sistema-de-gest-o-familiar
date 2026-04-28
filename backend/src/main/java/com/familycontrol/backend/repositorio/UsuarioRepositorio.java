package com.familycontrol.backend.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.familycontrol.backend.modelo.entidade.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
    
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
