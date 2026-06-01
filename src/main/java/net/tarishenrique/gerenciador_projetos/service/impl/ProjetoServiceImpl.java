package net.tarishenrique.gerenciador_projetos.service.impl;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.exception.ProjetoComStatusNaoExcluir;
import net.tarishenrique.gerenciador_projetos.exception.ProjetoNaoEncontradoException;
import net.tarishenrique.gerenciador_projetos.exception.StatusInvalidoException;
import net.tarishenrique.gerenciador_projetos.mapper.ProjetoMapper;
import net.tarishenrique.gerenciador_projetos.model.Projeto;
import net.tarishenrique.gerenciador_projetos.model.StatusProjeto;
import net.tarishenrique.gerenciador_projetos.repository.ProjetoRepository;
import net.tarishenrique.gerenciador_projetos.service.MembroService;
import net.tarishenrique.gerenciador_projetos.service.ProjetoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;
    private final ProjetoMapper projetoMapper;
    private final MembroService membroService;

    public ProjetoServiceImpl(
            ProjetoRepository repository,
            ProjetoMapper projetoMapper,
            MembroService membroService) {
        this.repository = repository;
        this.projetoMapper = projetoMapper;
        this.membroService = membroService;
    }

    @Transactional
    @Override
    public ProjetoResponseDTO salvar(ProjetoRequestDTO projetoRequestDTO) {
        Projeto projeto = projetoMapper.toEntity(projetoRequestDTO);

        membroService.validarGerente(projeto.getGerenteId());

        if (projeto.getStatus() != StatusProjeto.EM_ANALISE) {
            throw new StatusInvalidoException(projeto.getStatus().name());
        }

        Projeto projetoSalvo = repository.save(projeto);

        return projetoMapper.toDto(projetoSalvo);
    }

    @Override
    public List<ProjetoResponseDTO> listar() {

        return repository.findAll()
                .stream()
                .map(projetoMapper::toDto)
                .toList();
    }

    @Override
    public ProjetoResponseDTO buscarPorId(Long projetoId) {
        Projeto projeto = repository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));
        return projetoMapper.toDto(projeto);
    }

    @Transactional
    @Override
    public ProjetoResponseDTO atualizar(Long projetoId, ProjetoRequestDTO projetoRequestDTO) {

        Projeto projetoSalvo = repository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));

        if (!projetoSalvo.getStatus().podeAlterarStatus(projetoRequestDTO.status())) {
            throw new StatusInvalidoException(projetoRequestDTO.status().name());
        }

        projetoMapper.updateEntityFromDto(projetoRequestDTO, projetoSalvo);
        Projeto projetoAtualizado = repository.save(projetoSalvo);
        return projetoMapper.toDto(projetoAtualizado);
    }

    @Transactional
    @Override
    public void deletar(Long projetoId) {
        Projeto projeto = repository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));

        if (!projeto.getStatus().podeSerExcluido()){
            throw new ProjetoComStatusNaoExcluir(projeto.getStatus().name());
        }

        repository.delete(projeto);
    }


}
