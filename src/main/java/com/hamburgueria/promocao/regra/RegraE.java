package com.hamburgueria.promocao.regra;

import com.hamburgueria.promocao.ContextoPromocao;

public class RegraE implements ExpressaoRegra {

    private final ExpressaoRegra esquerda;
    private final ExpressaoRegra direita;

    public RegraE(ExpressaoRegra esquerda, ExpressaoRegra direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean interpretar(ContextoPromocao contexto) {
        return esquerda.interpretar(contexto) && direita.interpretar(contexto);
    }
}
