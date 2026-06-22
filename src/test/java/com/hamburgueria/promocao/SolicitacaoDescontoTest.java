package com.hamburgueria.promocao;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolicitacaoDescontoTest {

    @Test
    void expoeValorEMotivo() {
        SolicitacaoDesconto s = new SolicitacaoDesconto(new BigDecimal("25.00"), "fidelidade");
        assertEquals(new BigDecimal("25.00"), s.getValor());
        assertEquals("fidelidade", s.getMotivo());
    }
}
