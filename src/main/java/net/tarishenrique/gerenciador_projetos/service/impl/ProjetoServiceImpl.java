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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    private final ModelMapper modelMapper;
    private final ProjetoRepository repository;
    private final APIClient apiClient;
    private final ProjetoMapper projetoMapper;

    public ProjetoServiceImpl(
            ModelMapper modelMapper,
            ProjetoRepository repository,
            APIClient apiClient,
            ProjetoMapper projetoMapper
    ) {
        this.modelMapper = modelMapper;
        this.repository = repository;
        this.apiClient = apiClient;
    }


    @Override
    public ProjetoResponseDTO salvar(ProjetoRequestDTO projetoRequestDTO) {

        Projeto projeto = modelMapper.map(projetoRequestDTO, Projeto.class);

        MembroResponseDTO membroResponseDTO = apiClient.getMembroById(projeto.getGerenteId());

        if(membroResponseDTO.id() != null || membroResponseDTO.atribuicao().equals("gerente")){
            throw GerenteNaoEncontradoException;
        }

        Projeto projetoSalvo = repository.save(projeto);

        ProjetoResponseDTO projetoResponseDTO = projetoMapper.toDto(projetoSalvo);

        return projetoResponseDTO;
    }
}
