package com.hamburgueria.notificacao;

import com.hamburgueria.pedido.Pedido;

public class NotificacaoPedidoConfirmado extends Notificacao {

    public NotificacaoPedidoConfirmado(CanalEnvio canal) {
        super(canal);
    }

    @Override
    protected String montarMensagem(Pedido pedido) {
        return "Pedido #" + pedido.getId() + " confirmado e ja esta sendo preparado.";
    }
}
