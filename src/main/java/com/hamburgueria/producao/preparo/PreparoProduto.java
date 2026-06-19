package com.hamburgueria.producao.preparo;

import java.util.ArrayList;
import java.util.List;

public abstract class PreparoProduto {

    public final List<String> preparar() {
        List<String> etapas = new ArrayList<>();
        etapas.add(separarInsumos());
        etapas.add(processar());
        if (exigeMontagem()) {
            etapas.add(montar());
        }
        etapas.add(finalizar());
        return etapas;
    }

    public abstract int tempoEstimadoMinutos();

    protected abstract String separarInsumos();

    protected abstract String processar();

    protected boolean exigeMontagem() {
        return true;
    }

    protected String montar() {
        return "Montando o produto";
    }

    protected String finalizar() {
        return "Conferindo e liberando para expedicao";
    }
}
