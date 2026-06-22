package com.hamburgueria.relatorio;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioFaturamento implements RelatorioGerencial {

    private final List<BigDecimal> vendas;
    private int execucoes;

    public RelatorioFaturamento(List<BigDecimal> vendas) {
        this.vendas = vendas;
    }

    @Override
    public String gerar() {
        execucoes++;
        BigDecimal total = vendas.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        return "Faturamento de " + vendas.size() + " pedidos: R$ " + total;
    }

    public int getExecucoes() {
        return execucoes;
    }
}
