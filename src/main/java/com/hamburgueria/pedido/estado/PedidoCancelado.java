package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

public class PedidoCancelado implements EstadoPedido {

    @Override
    public StatusPedido getStatus() {
        return StatusPedido.CANCELADO;
    }

    @Override
    public void avancar(Pedido pedido) {
        throw new TransicaoInvalidaException(getStatus(), "avancar");
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new TransicaoInvalidaException(getStatus(), "cancelar");
    }
}
