package net.tarishenrique.gerenciador_projetos.repository;

import net.tarishenrique.gerenciador_projetos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
