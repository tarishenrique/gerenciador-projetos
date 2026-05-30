package net.tarishenrique.gerenciador_projetos.service.impl;

import net.tarishenrique.gerenciador_projetos.dto.MembroResponseDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.exception.GerenteNaoEncontradoException;
import net.tarishenrique.gerenciador_projetos.exception.ProjetoComStatusNaoExcluir;
import net.tarishenrique.gerenciador_projetos.exception.ProjetoNaoEncontradoException;
import net.tarishenrique.gerenciador_projetos.exception.StatusInvalidoException;
import net.tarishenrique.gerenciador_projetos.mapper.ProjetoMapper;
import net.tarishenrique.gerenciador_projetos.model.Projeto;
import net.tarishenrique.gerenciador_projetos.model.StatusProjeto;
import net.tarishenrique.gerenciador_projetos.repository.ProjetoRepository;
import net.tarishenrique.gerenciador_projetos.service.APIClient;
import net.tarishenrique.gerenciador_projetos.service.ProjetoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;
    private final APIClient apiClient;
    private final ProjetoMapper projetoMapper;

    public ProjetoServiceImpl(
            ProjetoRepository repository,
            APIClient apiClient,
            ProjetoMapper projetoMapper) {
        this.repository = repository;
        this.apiClient = apiClient;
        this.projetoMapper = projetoMapper;
    }

    @Override
    public ProjetoResponseDTO salvar(ProjetoRequestDTO projetoRequestDTO) {
        Projeto projeto = projetoMapper.toEntity(projetoRequestDTO);

        MembroResponseDTO membroResponseDTO = apiClient.getMembroById(projeto.getGerenteId());

        if (membroResponseDTO == null
                || membroResponseDTO.membroId() == null
                || !"gerente".equalsIgnoreCase(membroResponseDTO.cargo())) {
            throw new GerenteNaoEncontradoException(projeto.getGerenteId());
        }

        if (!StatusProjeto.EM_ANALISE.name().equals(projeto.getStatus().name())) {
            throw new StatusInvalidoException(projeto.getStatus().name());
        }

        Projeto projetoSalvo = repository.save(projeto);

        return projetoMapper.toDto(projetoSalvo);
    }

    @Override
    public List<ProjetoResponseDTO> listar() {
        List<ProjetoResponseDTO> projetos = repository.findAll()
                .stream()
                .map(projetoMapper::toDto)
                .toList();

        return projetos;
    }

    @Override
    public ProjetoResponseDTO buscarPorId(Long projetoId) {
        Projeto projeto = repository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));
        return projetoMapper.toDto(projeto);
    }

    @Override
    public ProjetoResponseDTO atualizar(Long projetoId, ProjetoRequestDTO projetoRequestDTO) {

        Projeto projetoSalvo = repository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));

        int statusId = projetoRequestDTO.status().getOrdem();

        if (statusId != StatusProjeto.CANCELADO.getOrdem() &&
                statusId != projetoSalvo.getStatus().getOrdem() &&
                statusId != (projetoSalvo.getStatus().getOrdem() + 1)) {
            throw new StatusInvalidoException(String.valueOf(statusId));
        }

        projetoMapper.updateEntityFromDto(projetoRequestDTO, projetoSalvo);
        Projeto projetoAtualizado = repository.save(projetoSalvo);
        return projetoMapper.toDto(projetoAtualizado);
    }

    @Override
    public void deletar(Long projetoId) {
        Projeto projeto = repository.findById(projetoId)
                .orElseThrow(() -> new ProjetoNaoEncontradoException(projetoId));

        if (projeto.getStatus() == StatusProjeto.INICIADO ||
            projeto.getStatus() == StatusProjeto.EM_ANDAMENTO ||
            projeto.getStatus() == StatusProjeto.ENCERRADO){
            throw new ProjetoComStatusNaoExcluir(projeto.getStatus().name());
        }

        repository.delete(projeto);
    }
}
