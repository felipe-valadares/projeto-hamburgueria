package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;
import com.hamburgueria.pedido.TipoEntrega;

public class PedidoPronto implements EstadoPedido {

    @Override
    public StatusPedido getStatus() {
        return StatusPedido.PRONTO;
    }

    @Override
    public void avancar(Pedido pedido) {
        if (pedido.getTipoEntrega() == TipoEntrega.DELIVERY) {
            pedido.transitarPara(new PedidoSaiuParaEntrega());
        } else {
            pedido.transitarPara(new PedidoEntregue());
        }
    }

    @Override
    public void cancelar(Pedido pedido) {
        pedido.transitarPara(new PedidoCancelado());
    }
}
