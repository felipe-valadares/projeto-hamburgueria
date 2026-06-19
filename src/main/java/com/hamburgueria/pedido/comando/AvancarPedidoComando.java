package com.hamburgueria.pedido.comando;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.estado.EstadoPedido;

public class AvancarPedidoComando implements Comando {

    private final Pedido pedido;
    private EstadoPedido estadoAnterior;

    public AvancarPedidoComando(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public void executar() {
        estadoAnterior = pedido.getEstado();
        pedido.avancar();
    }

    @Override
    public void desfazer() {
        pedido.transitarPara(estadoAnterior);
    }
}
