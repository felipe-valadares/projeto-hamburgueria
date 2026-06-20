package com.hamburgueria.pagamento;

import java.math.BigDecimal;

public class ComprovantePagamento {

    private final boolean aprovado;
    private final String autorizacao;
    private final BigDecimal valor;
    private final String meio;

    public ComprovantePagamento(boolean aprovado, String autorizacao, BigDecimal valor, String meio) {
        this.aprovado = aprovado;
        this.autorizacao = autorizacao;
        this.valor = valor;
        this.meio = meio;
    }

    public boolean isAprovado() {
        return aprovado;
    }

    public String getAutorizacao() {
        return autorizacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getMeio() {
        return meio;
    }
}
