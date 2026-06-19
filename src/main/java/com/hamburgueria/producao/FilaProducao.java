package com.hamburgueria.producao;

import com.hamburgueria.pedido.Pedido;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilaProducao implements Iterable<Pedido> {

    private final List<Pedido> pedidos = new ArrayList<>();

    public void inserir(Pedido pedido) {
        pedidos.add(pedido);
    }

    public int tamanho() {
        return pedidos.size();
    }

    public boolean isVazia() {
        return pedidos.isEmpty();
    }

    @Override
    public Iterator<Pedido> iterator() {
        return new IteradorPorPrioridade(pedidos);
    }
}
