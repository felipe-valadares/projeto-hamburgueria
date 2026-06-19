package com.hamburgueria.promocao;

import java.math.BigDecimal;

public class AprovacaoAtendente extends AprovadorDesconto {

    private static final BigDecimal LIMITE = new BigDecimal("10.00");

    @Override
    protected boolean podeAprovar(BigDecimal valor) {
        return valor.compareTo(LIMITE) <= 0;
    }

    @Override
    protected String getCargo() {
        return "Atendente";
    }
}
