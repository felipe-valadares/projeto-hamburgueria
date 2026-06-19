package com.hamburgueria.promocao;

import java.math.BigDecimal;

public class AprovacaoDono extends AprovadorDesconto {

    private static final BigDecimal LIMITE = new BigDecimal("200.00");

    @Override
    protected boolean podeAprovar(BigDecimal valor) {
        return valor.compareTo(LIMITE) <= 0;
    }

    @Override
    protected String getCargo() {
        return "Dono";
    }
}
