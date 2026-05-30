package net.tarishenrique.gerenciador_projetos.service;

import net.tarishenrique.gerenciador_projetos.dto.MembroResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MEMBRO-SERVICE", url = "https://6a1aebd7bc2f94475492cedf.mockapi.io")
public interface APIClient {

    @GetMapping("/api/v1/membros/{membroId}")
    MembroResponseDTO getMembroById(@PathVariable Long membroId);
}
