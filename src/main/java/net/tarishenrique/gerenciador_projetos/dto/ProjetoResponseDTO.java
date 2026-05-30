package net.tarishenrique.gerenciador_projetos.dto;

import net.tarishenrique.gerenciador_projetos.model.StatusProjeto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoResponseDTO(
        Long projetoId,
        String nome,
        LocalDate dataInicio,
        LocalDate previsaoTermino,
        LocalDate dataFimReal,
        BigDecimal orcamentoTotal,
        String descricao,
        Long gerenteId,
        StatusProjeto status
) {
}
