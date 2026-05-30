package net.tarishenrique.gerenciador_projetos.controller;

import net.tarishenrique.gerenciador_projetos.dto.ProjetoRequestDTO;
import net.tarishenrique.gerenciador_projetos.dto.ProjetoResponseDTO;
import net.tarishenrique.gerenciador_projetos.service.ProjetoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> salvar(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        ProjetoResponseDTO projetoSalvo = projetoService.salvar(projetoRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ProjetoResponseDTO>> listar() {
        List<ProjetoResponseDTO> projetos = projetoService.listar();
        return ResponseEntity.ok(projetos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> buscarPorId(@PathVariable Long id) {
        ProjetoResponseDTO projeto = projetoService.buscarPorId(id);
        return ResponseEntity.ok(projeto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjetoResponseDTO> atualizar(@PathVariable Long id,
            @RequestBody ProjetoRequestDTO projetoRequestDTO) {
        ProjetoResponseDTO projetoAtualizado = projetoService.atualizar(id, projetoRequestDTO);
        return ResponseEntity.ok(projetoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        projetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
