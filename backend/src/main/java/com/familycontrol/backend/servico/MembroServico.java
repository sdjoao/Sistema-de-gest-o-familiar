package com.familycontrol.backend.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.familycontrol.backend.excecao.ExcecaoNaoAutorizado;
import com.familycontrol.backend.excecao.ExcecaoNaoEncontrado;
import com.familycontrol.backend.modelo.dto.requisicao.MembroRequisicao;
import com.familycontrol.backend.modelo.dto.resposta.MembroResposta;
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
    
    public List<MembroResposta> listarMembrosPorFamilia(Long codigoFamilia){
        return membroRepositorio.findByFamiliaCodigoFamiliaOrderByNome(codigoFamilia)
            .stream()
            .map(MembroResposta::deEntidade)
            .toList();
    }

    public MembroResposta buscarMembroPorCodigo(Long codigoMembro, Long codigoFamilia){
        Membro membro = membroRepositorio.findById(codigoMembro)
            .orElseThrow(() -> new ExcecaoNaoEncontrado("Membro não encontrado."));

        if(!membro.getFamilia().getCodigoFamilia().equals(codigoFamilia)) {
            throw new ExcecaoNaoAutorizado("Acesso inválido, não autorizado a consultar outro código familia.");
        }

        return MembroResposta.deEntidade(membro);
    }

    public MembroResposta criarMembro(MembroRequisicao dadosDoMembro, Long codigoFamilia){
        Familia familia = familiaRepositorio.findByCodigoFamilia(codigoFamilia)
                            .orElseThrow(() -> new ExcecaoNaoEncontrado("Codigo familia não encontrado."));

        Membro membro = new Membro();
        membro.setNome(dadosDoMembro.nome());
        membro.setDataNascimento(dadosDoMembro.dataNascimento());
        membro.setTipoParentesco(dadosDoMembro.parentesco());
        membro.setTelefone(dadosDoMembro.telefone());
        membro.setUrlFoto(dadosDoMembro.urlFoto());
        membro.setFamilia(familia);
        membroRepositorio.save(membro);
        return MembroResposta.deEntidade(membro);
    }

    public MembroResposta atualizarMembro(MembroRequisicao dadosDoMembro, Long codigoMembro, Long codigoFamilia){
        Membro membro = membroRepositorio.findById(codigoMembro).orElseThrow(() -> new ExcecaoNaoEncontrado(" Membro não encontrado."));

        if(!membro.getFamilia().getCodigoFamilia().equals(codigoFamilia)) {
            throw new ExcecaoNaoAutorizado("Acesso inválido, não autorizado a alterar outro código familia.");
        }
        membro.setNome(dadosDoMembro.nome());
        membro.setDataNascimento(dadosDoMembro.dataNascimento());
        membro.setTipoParentesco(dadosDoMembro.parentesco());
        membro.setTelefone(dadosDoMembro.telefone());
        membro.setUrlFoto(dadosDoMembro.urlFoto());
        membroRepositorio.save(membro);
        return MembroResposta.deEntidade(membro);
    }

    public void deletarMembro(Long codigoMembro, Long codigoFamilia){
        Membro membro = membroRepositorio.findById(codigoMembro).orElseThrow(() -> new ExcecaoNaoEncontrado(" Membro não encontrado."));

        if(!membro.getFamilia().getCodigoFamilia().equals(codigoFamilia)) {
            throw new ExcecaoNaoAutorizado("Acesso inválido, não autorizado a alterar outro código familia.");
        }
        membro.setAtivo(false);
        membroRepositorio.save(membro);
    }
}
