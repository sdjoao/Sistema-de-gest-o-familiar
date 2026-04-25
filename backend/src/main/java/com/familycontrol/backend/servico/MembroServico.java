package com.familycontrol.backend.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.familycontrol.backend.modelo.entidade.Familia;
import com.familycontrol.backend.modelo.entidade.Membro;
import com.familycontrol.backend.repositorio.FamiliaRepositorio;
import com.familycontrol.backend.repositorio.MembroRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembroServico {

    private final MembroRepositorio membroRepositorio;
    private final FamiliaRepositorio familiaRepositorio;

    public List<Membro> listarMembroPorFamilia(Long codigoFamilia) {
        return membroRepositorio.findByCodigoFamiliaOrderByNome(codigoFamilia);
    }

    public Membro buscarMembroPorCodigo(Long codigo, Long codigoFamilia) {
        Membro membro = membroRepositorio.findById(codigo)
            .orElseThrow(() -> new RuntimeException("Membro não encontrado"));
        if(!membro.getFamilia().getCodigoFamilia().equals(codigoFamilia)){
            throw new RuntimeException("Acesso negado!");
        }
        return membro;
    }

    public Membro criar(Membro membro, Long codigoFamilia){
        Familia familia = familiaRepositorio.findbyCodigoFamilia(codigoFamilia)
            .orElseThrow(() -> new RuntimeException("Familia não encontrada"));
        
        membro.setFamilia(familia);

        return membroRepositorio.save(membro);
    }

    public Membro atualizar(Long codigoMembro, Membro membroComNovosDados, Long codigoFamilia){
        Membro membroExistente = buscarMembroPorCodigo(codigoMembro, codigoFamilia);

        membroExistente.setNome(membroComNovosDados.getNome());
        membroExistente.setDataNascimento(membroComNovosDados.getDataNascimento());
        membroExistente.setTipoParentesco(membroComNovosDados.getTipoParentesco());
        membroExistente.setTelefone(membroComNovosDados.getTelefone());
        membroExistente.setUrlFoto(membroComNovosDados.getUrlFoto());

        return membroRepositorio.save(membroExistente);
    }

    public void inativarMembro(Long codigoMembro, Long codigoFamilia){
        Membro membro = buscarMembroPorCodigo(codigoMembro, codigoFamilia);
        membro.setAtivo(false);
        membroRepositorio.save(membro);
    }
    
}
