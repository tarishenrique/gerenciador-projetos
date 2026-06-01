package net.tarishenrique.gerenciador_projetos.service;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjetoService {

    public ProjetoResponseDTO salvar(ProjetoRequestDTO projetoRequestDTO);

    Page<ProjetoResponseDTO> listar(Pageable pageable);

    ProjetoResponseDTO buscarPorId(Long id);

    ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO projetoRequestDTO);

    void deletar(Long id);
}
