package net.tarishenrique.gerenciador_projetos.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException(Long projetoId) {
        super("Projeto não encontrado: projetoId= " + projetoId);
    }
}
