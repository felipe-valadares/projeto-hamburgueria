package com.hamburgueria.producao.preparo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreparoProdutoTest {

    @Test
    void hamburguerSegueTodasAsEtapasIncluindoMontagem() {
        List<String> etapas = new PreparoHamburguer().preparar();

        assertEquals(4, etapas.size());
        assertTrue(etapas.get(0).startsWith("Separando"));
        assertTrue(etapas.get(1).contains("Grelhando"));
    }

    @Test
    void bebidaPulaEtapaDeMontagem() {
        List<String> etapas = new PreparoBebida().preparar();

        assertEquals(3, etapas.size());
        assertTrue(etapas.stream().noneMatch(e -> e.toLowerCase().contains("montando")));
    }

    @Test
    void cadaProdutoTemTempoEstimadoProprio() {
        assertTrue(new PreparoHamburguer().tempoEstimadoMinutos()
                > new PreparoBebida().tempoEstimadoMinutos());
    }
}
