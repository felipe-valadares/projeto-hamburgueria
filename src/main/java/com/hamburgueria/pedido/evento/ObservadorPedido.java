package com.hamburgueria.pedido.evento;

import com.hamburgueria.pedido.Pedido;

public interface ObservadorPedido {

    void statusAtualizado(Pedido pedido);
}
