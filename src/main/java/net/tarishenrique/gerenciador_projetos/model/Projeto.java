package net.tarishenrique.gerenciador_projetos.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projetoId;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate previsaoTermino;

    private LocalDate dataFimReal;

    @Column(nullable = false)
    private BigDecimal orcamentoTotal;

    private String descricao;

    private Long gerenteId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProjeto status;
}
