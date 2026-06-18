package com.hamburgueria.pedido.estado;

import com.hamburgueria.pedido.StatusPedido;

public class TransicaoInvalidaException extends RuntimeException {

    public TransicaoInvalidaException(StatusPedido status, String acao) {
        super("Nao e possivel " + acao + " um pedido com status " + status.getDescricao() + ".");
    }
}
