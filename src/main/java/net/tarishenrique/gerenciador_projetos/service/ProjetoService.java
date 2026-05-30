package net.tarishenrique.gerenciador_projetos.service;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;

public interface ProjetoService {

    public ProjetoResponseDTO salvar(ProjetoRequestDTO projetoRequestDTO);
}
