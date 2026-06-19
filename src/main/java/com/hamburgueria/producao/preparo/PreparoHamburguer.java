package com.hamburgueria.producao.preparo;

public class PreparoHamburguer extends PreparoProduto {

    @Override
    public int tempoEstimadoMinutos() {
        return 12;
    }

    @Override
    protected String separarInsumos() {
        return "Separando pao, carne e acompanhamentos";
    }

    @Override
    protected String processar() {
        return "Grelhando a carne na chapa";
    }

    @Override
    protected String montar() {
        return "Montando o lanche na ordem correta";
    }
}
