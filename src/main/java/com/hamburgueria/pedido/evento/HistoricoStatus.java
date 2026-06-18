package com.hamburgueria.pedido.evento;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoStatus implements ObservadorPedido {

    private final List<StatusPedido> transicoes = new ArrayList<>();

    @Override
    public void statusAtualizado(Pedido pedido) {
        transicoes.add(pedido.getStatus());
    }

    public List<StatusPedido> getTransicoes() {
        return Collections.unmodifiableList(transicoes);
    }

    public StatusPedido getUltimo() {
        return transicoes.isEmpty() ? null : transicoes.get(transicoes.size() - 1);
    }
}
