package net.tarishenrique.gerenciador_projetos.service.impl;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.mapper.ProjetoMapper;
import net.tarishenrique.gerenciador_projetos.model.Projeto;
import net.tarishenrique.gerenciador_projetos.model.StatusProjeto;
import net.tarishenrique.gerenciador_projetos.repository.ProjetoRepository;
import net.tarishenrique.gerenciador_projetos.service.MembroService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;



@ExtendWith(MockitoExtension.class)
class ProjetoServiceImplTest {

    @Mock
    private ProjetoRepository repository;

    @Mock
    private ProjetoMapper projetoMapper;

    @Mock
    private MembroService membroService;


    @InjectMocks
    private ProjetoServiceImpl projetoService;

//    public void salvarDeveRetornarUmaExcecaoSeNaoExistirGerente(){
//
//    }

    @Test
    void salvarDeveRetornarDTOQuandoDadosValidos() {

        ProjetoRequestDTO dto = new ProjetoRequestDTO(
                "Projeto Teste",
                LocalDate.now(),
                LocalDate.now().plusMonths(3),
                null,
                BigDecimal.TEN,
                "Descrição teste",
                1L,
                StatusProjeto.EM_ANALISE
        );

        Projeto projeto = Projeto.builder()
                .gerenteId(1L)
                .status(StatusProjeto.EM_ANALISE)
                .build();

        Projeto projetoSalvo = Projeto.builder()
                .projetoId(1L)
                .nome("Projeto Teste")
                .gerenteId(1L)
                .status(StatusProjeto.EM_ANALISE)
                .build();

        ProjetoResponseDTO responseEsperado = new ProjetoResponseDTO(
                1L, "Projeto Teste", LocalDate.now(), LocalDate.now().plusMonths(3),
                null, BigDecimal.TEN, "Descrição teste", 1L, StatusProjeto.EM_ANALISE
        );

        given(projetoMapper.toEntity(dto)).willReturn(projeto);
        given(repository.save(any(Projeto.class))).willReturn(projetoSalvo);
        given(projetoMapper.toDto(projetoSalvo)).willReturn(responseEsperado);

        ProjetoResponseDTO resultado = projetoService.salvar(dto);

        assertThat(resultado).isNotNull();
        assertThat(resultado.projetoId()).isEqualTo(1L);
        assertThat(resultado.nome()).isEqualTo("Projeto Teste");
        assertThat(resultado.status()).isEqualTo(StatusProjeto.EM_ANALISE);
    }
}