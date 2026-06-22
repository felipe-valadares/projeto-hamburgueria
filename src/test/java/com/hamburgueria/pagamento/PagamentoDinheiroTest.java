package com.hamburgueria.pagamento;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PagamentoDinheiroTest {

    @Test
    void calculaTrocoCorretamente() {
        PagamentoDinheiro d = new PagamentoDinheiro(new BigDecimal("50.00"));
        assertEquals(new BigDecimal("10.00"), d.calcularTroco(new BigDecimal("40.00")));
    }
}
