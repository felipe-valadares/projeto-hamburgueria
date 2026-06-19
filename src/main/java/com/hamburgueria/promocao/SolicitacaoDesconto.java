package com.hamburgueria.promocao;

import java.math.BigDecimal;

public class SolicitacaoDesconto {

    private final BigDecimal valor;
    private final String motivo;

    public SolicitacaoDesconto(BigDecimal valor, String motivo) {
        this.valor = valor;
        this.motivo = motivo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getMotivo() {
        return motivo;
    }
}
