package net.tarishenrique.gerenciador_projetos.model;

public enum StatusProjeto {
    EM_ANALISE(1),
    ANALISE_REALIZADA(2),
    ANALISE_APROVADA(3),
    INICIADO(4),
    PLANEJADO(5),
    EM_ANDAMENTO(6),
    ENCERRADO(7),
    CANCELADO(8);

    private final int ordem;

    StatusProjeto(int ordem) {
        this.ordem = ordem;
    }

    public int getOrdem() {
        return ordem;
    }
}
