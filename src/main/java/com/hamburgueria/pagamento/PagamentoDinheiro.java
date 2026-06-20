package com.hamburgueria.pagamento;

import java.math.BigDecimal;

public class PagamentoDinheiro implements MeioPagamento {

    private final BigDecimal valorRecebido;

    public PagamentoDinheiro(BigDecimal valorRecebido) {
        this.valorRecebido = valorRecebido;
    }

    @Override
    public ComprovantePagamento cobrar(BigDecimal valor) {
        if (valorRecebido.compareTo(valor) < 0) {
            return new ComprovantePagamento(false, "-", valor, "Dinheiro");
        }
        return new ComprovantePagamento(true, "DINHEIRO", valor, "Dinheiro");
    }

    public BigDecimal calcularTroco(BigDecimal valor) {
        return valorRecebido.subtract(valor);
    }
}
