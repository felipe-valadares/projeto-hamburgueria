package com.hamburgueria.operacao;

import com.hamburgueria.pedido.Pedido;

public class Expedicao extends SetorOperacao {

    public void despachar(Pedido pedido) {
        pedido.avancar();
        mediador.notificar(this, pedido, EventoOperacao.PEDIDO_DESPACHADO);
    }
}
