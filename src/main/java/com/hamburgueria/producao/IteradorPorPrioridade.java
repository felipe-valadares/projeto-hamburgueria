package com.hamburgueria.producao;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class IteradorPorPrioridade implements Iterator<Pedido> {

    private final List<Pedido> ordenados;
    private int posicao;

    IteradorPorPrioridade(List<Pedido> pedidos) {
        this.ordenados = new ArrayList<>(pedidos);
        this.ordenados.sort(Comparator
                .comparingInt(IteradorPorPrioridade::prioridade)
                .thenComparing(Pedido::getMomento));
    }

    @Override
    public boolean hasNext() {
        return posicao < ordenados.size();
    }

    @Override
    public Pedido next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return ordenados.get(posicao++);
    }

    private static int prioridade(Pedido pedido) {
        TipoEntrega tipo = pedido.getTipoEntrega();
        if (tipo == TipoEntrega.BALCAO || tipo == TipoEntrega.DRIVE_THRU) {
            return 0;
        }
        if (tipo == TipoEntrega.RETIRADA) {
            return 1;
        }
        return 2;
    }
}
