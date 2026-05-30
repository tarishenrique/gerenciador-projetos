package net.tarishenrique.gerenciador_projetos.exception;

public class StatusInvalidoExcpetion extends RuntimeException {

    public StatusInvalidoExcpetion(String status) {
        super("Status de Projeto inválido: Status = " + status);
    }
}
