package com.hamburgueria.infraestrutura;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfiguracaoTest {

    @Test
    void mantemUmaUnicaInstanciaCompartilhada() {
        Configuracao primeira = Configuracao.getInstancia();
        Configuracao segunda = Configuracao.getInstancia();

        assertSame(primeira, segunda);
    }

    @Test
    void alteracaoEhVisivelEmTodaAplicacao() {
        Configuracao.getInstancia().setFreteBase(new BigDecimal("7.50"));

        assertEquals(new BigDecimal("7.50"), Configuracao.getInstancia().getFreteBase());
    }

    @Test
    void reconheceHorarioDeFuncionamento() {
        Configuracao config = Configuracao.getInstancia();

        assertTrue(config.estaAberta(LocalTime.of(20, 0)));
        assertFalse(config.estaAberta(LocalTime.of(9, 0)));
    }
}
