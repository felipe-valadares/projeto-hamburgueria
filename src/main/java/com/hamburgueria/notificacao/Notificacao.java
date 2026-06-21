package com.hamburgueria.notificacao;

import com.hamburgueria.pedido.Pedido;

public abstract class Notificacao {

    private final CanalEnvio canal;

    protected Notificacao(CanalEnvio canal) {
        this.canal = canal;
    }

    public String enviar(Pedido pedido) {
        return canal.enviar(pedido.getCliente(), montarMensagem(pedido));
    }

    protected abstract String montarMensagem(Pedido pedido);
}
