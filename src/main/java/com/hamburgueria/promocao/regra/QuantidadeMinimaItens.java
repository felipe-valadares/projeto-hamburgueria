package com.hamburgueria.promocao.regra;

import com.hamburgueria.promocao.ContextoPromocao;

public class QuantidadeMinimaItens implements ExpressaoRegra {

    private final int minimo;

    public QuantidadeMinimaItens(int minimo) {
        this.minimo = minimo;
    }

    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return contexto.getQuantidadeItens() >= minimo;
    }
}
