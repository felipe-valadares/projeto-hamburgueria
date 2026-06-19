package com.hamburgueria.promocao;

import java.math.BigDecimal;

public class ContextoPromocao {

    private final BigDecimal subtotal;
    private final int quantidadeItens;

    public ContextoPromocao(BigDecimal subtotal, int quantidadeItens) {
        this.subtotal = subtotal;
        this.quantidadeItens = quantidadeItens;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public int getQuantidadeItens() {
        return quantidadeItens;
    }
}
