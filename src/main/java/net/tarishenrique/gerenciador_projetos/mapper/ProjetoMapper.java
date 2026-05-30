package net.tarishenrique.gerenciador_projetos.mapper;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.model.Projeto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjetoMapper {

    ProjetoResponseDTO toDto(Projeto projeto);

    Projeto toEntity(ProjetoRequestDTO projetoRequestDTO);

    void updateEntityFromDto(ProjetoRequestDTO dto, @MappingTarget Projeto projeto);
}
