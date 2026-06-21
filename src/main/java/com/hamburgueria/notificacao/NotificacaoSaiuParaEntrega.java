package com.hamburgueria.notificacao;

import com.hamburgueria.pedido.Pedido;

public class NotificacaoSaiuParaEntrega extends Notificacao {

    public NotificacaoSaiuParaEntrega(CanalEnvio canal) {
        super(canal);
    }

    @Override
    protected String montarMensagem(Pedido pedido) {
        return "Pedido #" + pedido.getId() + " saiu para entrega e chega em instantes.";
    }
}
