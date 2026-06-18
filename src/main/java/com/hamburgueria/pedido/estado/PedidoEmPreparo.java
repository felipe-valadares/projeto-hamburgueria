package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

public class PedidoEmPreparo implements EstadoPedido {

    @Override
    public StatusPedido getStatus() {
        return StatusPedido.EM_PREPARO;
    }

    @Override
    public void avancar(Pedido pedido) {
        pedido.transitarPara(new PedidoPronto());
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.transitarPara(new PedidoCancelado());
    }
}
