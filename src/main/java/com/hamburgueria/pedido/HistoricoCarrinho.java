package com.hamburgueria.pedido;

import java.util.ArrayDeque;
import java.util.Deque;

public class HistoricoCarrinho {

    private final Deque<MementoCarrinho> estados = new ArrayDeque<>();

    public void salvar(Carrinho carrinho) {
        estados.push(carrinho.salvar());
    }

    public void desfazer(Carrinho carrinho) {
        if (estados.isEmpty()) {
            return;
        }
        carrinho.restaurar(estados.pop());
    }

    public boolean podeDesfazer() {
        return !estados.isEmpty();
    }
}
