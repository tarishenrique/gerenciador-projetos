package net.tarishenrique.gerenciador_projetos.exception;

public class ProjetoComStatusNaoExcluir extends RuntimeException {
    public ProjetoComStatusNaoExcluir(String status) {
        super("O projeto possui um status que não permite a exclusão: Status = " + status);
    }
}
