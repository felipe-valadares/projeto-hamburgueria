package com.hamburgueria.promocao;

import com.hamburgueria.promocao.regra.SubtotalMinimo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CupomCompletoTest {

    @Test
    void getCodigoRetornaOCodigoDoCupom() {
        Cupom c = new Cupom("DESC15", new BigDecimal("0.15"), new SubtotalMinimo(BigDecimal.ZERO));
        assertEquals("DESC15", c.getCodigo());
    }
}
