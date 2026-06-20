package com.hamburgueria.pagamento;

import com.hamburgueria.infraestrutura.GeradorId;

import java.math.BigDecimal;

public class PagamentoPix implements MeioPagamento {

    private final String chave;

    public PagamentoPix(String chave) {
        this.chave = chave;
    }

    @Override
    public ComprovantePagamento cobrar(BigDecimal valor) {
        String autorizacao = "PIX-" + GeradorId.proximo();
        return new ComprovantePagamento(true, autorizacao, valor, "Pix (" + chave + ")");
    }
}
