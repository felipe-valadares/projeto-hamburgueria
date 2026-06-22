package com.hamburgueria.pagamento;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComprovantePagamentoTest {

    @Test
    void expoeAtributosCorretamente() {
        ComprovantePagamento c = new ComprovantePagamento(true, "AUTH-123", new BigDecimal("50.00"), "Pix");
        assertEquals("AUTH-123", c.getAutorizacao());
        assertEquals(new BigDecimal("50.00"), c.getValor());
        assertEquals("Pix", c.getMeio());
    }
}
