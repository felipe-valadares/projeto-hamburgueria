package com.hamburgueria.pagamento;

import java.math.BigDecimal;

public class Pagamento {

    private final BigDecimal valor;
    private final MeioPagamento meio;
    private StatusPagamento status = StatusPagamento.PENDENTE;
    private ComprovantePagamento comprovante;

    public Pagamento(BigDecimal valor, MeioPagamento meio) {
        this.valor = valor;
        this.meio = meio;
    }

    public ComprovantePagamento processar() {
        if (status != StatusPagamento.PENDENTE) {
            throw new PagamentoInvalidoException("Pagamento ja foi processado.");
        }
        comprovante = meio.cobrar(valor);
        status = comprovante.isAprovado() ? StatusPagamento.PAGO : StatusPagamento.RECUSADO;
        return comprovante;
    }

    public void estornar() {
        if (status != StatusPagamento.PAGO) {
            throw new PagamentoInvalidoException("Apenas pagamentos aprovados podem ser estornados.");
        }
        status = StatusPagamento.ESTORNADO;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public ComprovantePagamento getComprovante() {
        return comprovante;
    }
}
