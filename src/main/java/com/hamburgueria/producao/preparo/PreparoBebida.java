package com.hamburgueria.producao.preparo;

public class PreparoBebida extends PreparoProduto {

    @Override
    public int tempoEstimadoMinutos() {
        return 2;
    }

    @Override
    protected String separarInsumos() {
        return "Pegando a bebida no refrigerador";
    }

    @Override
    protected String processar() {
        return "Servindo com gelo";
    }

    @Override
    protected boolean exigeMontagem() {
        return false;
    }
}
