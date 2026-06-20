package com.hamburgueria.pagamento.gateway;

import com.hamburgueria.pagamento.ComprovantePagamento;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GatewayPagamentoAdapterTest {

    @Test
    void traduzRetornoAprovadoDoGatewayParaComprovante() {
        GatewayPagamentoAdapter adapter = new GatewayPagamentoAdapter(
                new GatewayPagamentosExterno(), "Visa", "4111111111111111");

        ComprovantePagamento comprovante = adapter.cobrar(new BigDecimal("50.00"));

        assertTrue(comprovante.isAprovado());
        assertTrue(comprovante.getAutorizacao().contains("VISA"));
    }

    @Test
    void traduzRecusaDoGatewayParaComprovanteNaoAprovado() {
        GatewayPagamentoAdapter adapter = new GatewayPagamentoAdapter(
                new GatewayPagamentosExterno(), "Master", "5500000000000000");

        ComprovantePagamento comprovante = adapter.cobrar(new BigDecimal("50.00"));

        assertFalse(comprovante.isAprovado());
    }
}
