package net.tarishenrique.gerenciador_projetos.dto;

import net.tarishenrique.gerenciador_projetos.model.StatusProjeto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoResponseDTO(
        Long id,
        String nome,
        LocalDate dataInicio,
        LocalDate previsaoTermino,
        LocalDate dataFimReal,
        BigDecimal orcamentoTotal,
        String descricao,
        GerenteResponseDTO gerente,
        StatusProjeto status
) {
}
