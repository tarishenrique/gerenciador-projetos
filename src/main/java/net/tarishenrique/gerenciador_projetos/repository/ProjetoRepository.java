package net.tarishenrique.gerenciador_projetos.repository;

import net.tarishenrique.gerenciador_projetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
