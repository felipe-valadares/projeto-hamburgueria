package com.hamburgueria.promocao.regra;

import com.hamburgueria.promocao.ContextoPromocao;

import java.math.BigDecimal;

public class SubtotalMinimo implements ExpressaoRegra {

    private final BigDecimal minimo;

    public SubtotalMinimo(BigDecimal minimo) {
        this.minimo = minimo;
    }

    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return contexto.getSubtotal().compareTo(minimo) >= 0;
    }
}
