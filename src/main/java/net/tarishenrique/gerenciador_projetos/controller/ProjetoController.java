package net.tarishenrique.gerenciador_projetos.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Projetos", description = "API de projetos")
@RestController
@RequestMapping("api/v1/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @Operation(summary = "Salvar um novo projeto", description = "Salva um novo projeto, deve ser enviado o id do gerente e o status EM ANALISE", responses = {
            @ApiResponse(responseCode = "201", description = "Projeto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar projeto"),
            @ApiResponse(responseCode = "500", description = "Erro ao criar projeto")
    })
    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> salvar(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        ProjetoResponseDTO projetoSalvo = projetoService.salvar(projetoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @Operation(summary = "Listar todos os projetos", description = "Lista todos os projetos", responses = {
            @ApiResponse(responseCode = "200", description = "Projetos listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao listar projetos")
    })
    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> listar() {
        List<ProjetoResponseDTO> projetos = projetoService.listar();
        return ResponseEntity.ok(projetos);
    }

    @Operation(summary = "Buscar projeto por ID", description = "Busca um projeto pelo seu ID", responses = {
            @ApiResponse(responseCode = "200", description = "Projeto encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar projeto")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProjetoResponseDTO projeto = projetoService.buscarPorId(id);
        return ResponseEntity.ok(projeto);
    }

    @Operation(summary = "Atualizar projeto", description = "Atualiza um projeto", responses = {
            @ApiResponse(responseCode = "200", description = "Projeto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao atualizar projeto")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody ProjetoRequestDTO projetoRequestDTO) {
        ProjetoResponseDTO projetoAtualizado = projetoService.atualizar(id, projetoRequestDTO);
        return ResponseEntity.ok(projetoAtualizado);
    }

    @Operation(summary = "Deletar projeto", description = "Deleta um projeto", responses = {
            @ApiResponse(responseCode = "204", description = "Projeto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao deletar projeto")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
