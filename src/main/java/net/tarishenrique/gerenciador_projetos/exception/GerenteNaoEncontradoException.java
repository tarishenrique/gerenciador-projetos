package net.tarishenrique.gerenciador_projetos.exception;

public class GerenteNaoEncontradoException extends RuntimeException {

    public GerenteNaoEncontradoException(Long gerenteId) {
        super("Gerente não encontrado ou membro sem atribuição de gerente. id=" + gerenteId);
    }
}
