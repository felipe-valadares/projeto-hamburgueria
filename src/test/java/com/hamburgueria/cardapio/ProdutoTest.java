package com.hamburgueria.cardapio;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProdutoTest {

    @Test
    void bebidaDescreveNomeComVolume() {
        Bebida bebida = new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false);
        assertEquals("Refrigerante 350ml", bebida.getDescricao());
        assertEquals(350, bebida.getMililitros());
    }

    @Test
    void acompanhamentoUsaNomeComoDescricao() {
        Acompanhamento a = new Acompanhamento("Batata Frita", new BigDecimal("12.00"), 400);
        assertEquals("Batata Frita", a.getDescricao());
    }

    @Test
    void sobremesaUsaNomeComoDescricao() {
        Sobremesa s = new Sobremesa("Milkshake", new BigDecimal("15.00"), 320);
        assertEquals("Milkshake", s.getDescricao());
    }
}
