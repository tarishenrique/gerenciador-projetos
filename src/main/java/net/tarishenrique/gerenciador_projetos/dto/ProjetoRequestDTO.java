package net.tarishenrique.gerenciador_projetos.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import net.tarishenrique.gerenciador_projetos.model.StatusProjeto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjetoRequestDTO(
        @NotBlank String nome,
        @NotNull LocalDate dataInicio,
        @NotNull LocalDate previsaoTermino,
        LocalDate dataFimReal,
        @NotNull @DecimalMin("0.0") BigDecimal orcamentoTotal,
        String descricao,
        @NotNull Long gerenteId,
        StatusProjeto status
) {
}
