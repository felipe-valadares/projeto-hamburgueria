package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

public interface EstadoPedido {

    StatusPedido getStatus();

    void avancar(Pedido pedido);

    void cancelar(Pedido pedido);
}
