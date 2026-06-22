package com.hamburgueria.producao.preparo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreparoAcompanhamentoTest {

    @Test
    void segueTodasAsEtapasIncluindoMontagem() {
        PreparoAcompanhamento preparo = new PreparoAcompanhamento();
        List<String> etapas = preparo.preparar();

        assertEquals(4, etapas.size());
        assertTrue(etapas.get(0).contains("porcao congelada"));
        assertTrue(etapas.get(1).contains("Fritando"));
        assertTrue(etapas.get(2).contains("Empacotando"));
    }

    @Test
    void tempoEstimadoCorrespondeAoProduto() {
        assertEquals(7, new PreparoAcompanhamento().tempoEstimadoMinutos());
    }
}
