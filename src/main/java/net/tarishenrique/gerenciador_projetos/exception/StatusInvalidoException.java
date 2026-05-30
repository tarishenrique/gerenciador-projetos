package net.tarishenrique.gerenciador_projetos.exception;

public class StatusInvalidoException extends RuntimeException {

    public StatusInvalidoException(String status) {
        super("Status de Projeto inválido: Status = " + status);
    }
}
