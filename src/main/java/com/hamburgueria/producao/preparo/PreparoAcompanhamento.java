package com.hamburgueria.producao.preparo;

public class PreparoAcompanhamento extends PreparoProduto {

    @Override
    public int tempoEstimadoMinutos() {
        return 7;
    }

    @Override
    protected String separarInsumos() {
        return "Separando a porcao congelada";
    }

    @Override
    protected String processar() {
        return "Fritando na imersao";
    }

    @Override
    protected String montar() {
        return "Empacotando a porcao";
    }
}
