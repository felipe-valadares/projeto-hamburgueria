package com.hamburgueria.pedido;

import java.util.ArrayList;
import java.util.List;

public final class MementoCarrinho {

    private final List<ItemPedido> itens;

    MementoCarrinho(List<ItemPedido> itens) {
        this.itens = new ArrayList<>();
        for (ItemPedido item : itens) {
            this.itens.add(item.copiar());
        }
    }

    List<ItemPedido> getItens() {
        List<ItemPedido> copia = new ArrayList<>();
        for (ItemPedido item : itens) {
            copia.add(item.copiar());
        }
        return copia;
    }
}
