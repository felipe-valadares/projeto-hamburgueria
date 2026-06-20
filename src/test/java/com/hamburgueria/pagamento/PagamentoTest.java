package com.hamburgueria.pagamento;

import com.hamburgueria.pagamento.gateway.GatewayPagamentoAdapter;
import com.hamburgueria.pagamento.gateway.GatewayPagamentosExterno;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PagamentoTest {

    @Test
    void pagamentoEmDinheiroAprovadoFicaPago() {
        Pagamento pagamento = new Pagamento(new BigDecimal("40.00"),
                new PagamentoDinheiro(new BigDecimal("50.00")));

        pagamento.processar();

        assertEquals(StatusPagamento.PAGO, pagamento.getStatus());
    }

    @Test
    void pagamentoComCartaoRecusadoFicaRecusado() {
        Pagamento pagamento = new Pagamento(new BigDecimal("40.00"),
                new GatewayPagamentoAdapter(new GatewayPagamentosExterno(), "Elo", "6000000000000000"));

        pagamento.processar();

        assertEquals(StatusPagamento.RECUSADO, pagamento.getStatus());
    }

    @Test
    void pagamentoPagoPodeSerEstornado() {
        Pagamento pagamento = new Pagamento(new BigDecimal("30.00"), new PagamentoPix("loja@brasaburger"));
        pagamento.processar();

        pagamento.estornar();

        assertEquals(StatusPagamento.ESTORNADO, pagamento.getStatus());
    }

    @Test
    void naoEstornaPagamentoNaoConcluido() {
        Pagamento pagamento = new Pagamento(new BigDecimal("30.00"),
                new PagamentoDinheiro(new BigDecimal("10.00")));
        pagamento.processar();

        assertThrows(PagamentoInvalidoException.class, pagamento::estornar);
    }

    @Test
    void naoProcessaPagamentoDuasVezes() {
        Pagamento pagamento = new Pagamento(new BigDecimal("30.00"), new PagamentoPix("loja@brasaburger"));
        pagamento.processar();

        assertThrows(PagamentoInvalidoException.class, pagamento::processar);
    }
}
