package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

public class PedidoRecebido implements EstadoPedido {

    @Override
    public StatusPedido getStatus() {
        return StatusPedido.RECEBIDO;
    }

    @Override
    public void avancar(Pedido pedido) {
        pedido.transitarPara(new PedidoEmPreparo());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.transitarPara(new PedidoCancelado());
    }
}
