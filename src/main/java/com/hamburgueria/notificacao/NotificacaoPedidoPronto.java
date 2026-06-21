package com.hamburgueria.notificacao;

import com.hamburgueria.pedido.Pedido;

public class NotificacaoPedidoPronto extends Notificacao {

    public NotificacaoPedidoPronto(CanalEnvio canal) {
        super(canal);
    }

    @Override
    protected String montarMensagem(Pedido pedido) {
        return "Pedido #" + pedido.getId() + " pronto! Ja pode ser retirado ou despachado.";
    }
}
