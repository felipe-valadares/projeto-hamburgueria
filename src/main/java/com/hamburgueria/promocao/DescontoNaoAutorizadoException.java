package com.hamburgueria.promocao;

import java.math.BigDecimal;

public class DescontoNaoAutorizadoException extends RuntimeException {

    public DescontoNaoAutorizadoException(BigDecimal valor) {
        super("Nenhum responsavel pode autorizar um desconto de R$ " + valor + ".");
    }
}
