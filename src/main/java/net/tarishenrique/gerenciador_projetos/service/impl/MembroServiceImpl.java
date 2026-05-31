package net.tarishenrique.gerenciador_projetos.service.impl;

import feign.FeignException;
import net.tarishenrique.gerenciador_projetos.dto.MembroResponseDTO;
import net.tarishenrique.gerenciador_projetos.exception.GerenteNaoEncontradoException;
import net.tarishenrique.gerenciador_projetos.service.APIClient;
import net.tarishenrique.gerenciador_projetos.service.MembroService;
import org.springframework.stereotype.Service;

@Service
public class MembroServiceImpl implements MembroService {

    private final APIClient apiClient;

    public MembroServiceImpl(APIClient apiClient){
        this.apiClient = apiClient;
    }

    @Override
    public void validarGerente(Long gerenteId) {

        if (gerenteId == null){
            throw new GerenteNaoEncontradoException(gerenteId);
        }

        try {
            MembroResponseDTO membro = apiClient.getMembroById(gerenteId);

            if (membro == null || membro.membroId() == null || "gerente".equalsIgnoreCase(membro.cargo())){
                throw new GerenteNaoEncontradoException(gerenteId);
            }
        } catch (FeignException.NotFound e){
            throw new GerenteNaoEncontradoException(gerenteId);
        } catch (FeignException e) {
            throw new IllegalStateException("membro-service está indisponível", e);
        }

    }
}
