package net.tarishenrique.gerenciador_projetos.service;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;

import java.util.List;

public interface ProjetoService {

    public ProjetoResponseDTO salvar(ProjetoRequestDTO projetoRequestDTO);

    List<ProjetoResponseDTO> listar();

    ProjetoResponseDTO buscarPorId(Long id);

    ProjetoResponseDTO atualizar(Long id, ProjetoRequestDTO projetoRequestDTO);

    void deletar(Long id);
}
