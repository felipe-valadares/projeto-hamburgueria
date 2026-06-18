package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

public class PedidoSaiuParaEntrega implements EstadoPedido {

    @Override
    public StatusPedido getStatus() {
        return StatusPedido.SAIU_PARA_ENTREGA;
    }

    @Override
    public void avancar(Pedido pedido) {
        pedido.transitarPara(new PedidoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        throw new TransicaoInvalidaException(getStatus(), "cancelar");
    }
}
