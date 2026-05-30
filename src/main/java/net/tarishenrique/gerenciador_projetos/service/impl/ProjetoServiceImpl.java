package net.tarishenrique.gerenciador_projetos.service.impl;

import net.tarishenrique.gerenciador_projetos.dto.MembroResponseDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.exception.GerenteNaoEncontradoException;
import net.tarishenrique.gerenciador_projetos.mapper.ProjetoMapper;
import net.tarishenrique.gerenciador_projetos.model.Projeto;
import net.tarishenrique.gerenciador_projetos.repository.ProjetoRepository;
import net.tarishenrique.gerenciador_projetos.service.APIClient;
import net.tarishenrique.gerenciador_projetos.service.ProjetoService;
import org.springframework.stereotype.Service;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ProjetoRepository repository;
    private final APIClient apiClient;
    private final ProjetoMapper projetoMapper;

    public ProjetoServiceImpl(
            ProjetoRepository repository,
            APIClient apiClient,
            ProjetoMapper projetoMapper
    ) {
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

        Projeto projetoSalvo = repository.save(projeto);

        return projetoMapper.toDto(projetoSalvo);
    }
}
