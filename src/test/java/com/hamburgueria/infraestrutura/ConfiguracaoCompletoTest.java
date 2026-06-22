package com.hamburgueria.infraestrutura;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfiguracaoCompletoTest {

    @Test
    void nomeLojaEhPadraoEAlteravel() {
        Configuracao config = Configuracao.getInstancia();
        assertNotNull(config.getNomeLoja());
        config.setNomeLoja("Teste Burger");
        assertEquals("Teste Burger", config.getNomeLoja());
        config.setNomeLoja("Brasa Burger");
    }

    @Test
    void raioDeEntregaEhAcessivelEAlteravel() {
        Configuracao config = Configuracao.getInstancia();
        double original = config.getRaioEntregaKm();
        config.setRaioEntregaKm(10.0);
        assertEquals(10.0, config.getRaioEntregaKm());
        config.setRaioEntregaKm(original);
    }

    @Test
    void valorPorQuilometroEhAcessivelEAlteravel() {
        Configuracao config = Configuracao.getInstancia();
        config.setValorPorQuilometro(new BigDecimal("2.00"));
        assertEquals(new BigDecimal("2.00"), config.getValorPorQuilometro());
        config.setValorPorQuilometro(new BigDecimal("1.50"));
    }

    @Test
    void horariosDeAberturaEFechamentoSaoAcessiveis() {
        Configuracao config = Configuracao.getInstancia();
        assertNotNull(config.getAbertura());
        assertNotNull(config.getFechamento());
        assertTrue(config.getFechamento().isAfter(config.getAbertura()));
    }
}
