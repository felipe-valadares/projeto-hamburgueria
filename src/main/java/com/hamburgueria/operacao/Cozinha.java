package com.hamburgueria.operacao;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

public class Cozinha extends SetorOperacao {

    public void preparar(Pedido pedido) {
        if (pedido.getStatus() == StatusPedido.RECEBIDO) {
            pedido.avancar();
        }
        pedido.avancar();
        mediador.notificar(this, pedido, EventoOperacao.PEDIDO_PRONTO);
    }
}
