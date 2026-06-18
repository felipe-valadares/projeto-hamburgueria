package com.hamburgueria.pedido;

public enum StatusPedido {
    RECEBIDO("Recebido"),
    EM_PREPARO("Em preparo"),
    PRONTO("Pronto"),
    SAIU_PARA_ENTREGA("Saiu para entrega"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isFinal() {
        return this == ENTREGUE || this == CANCELADO;
    }
}
