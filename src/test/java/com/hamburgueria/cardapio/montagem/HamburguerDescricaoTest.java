package com.hamburgueria.cardapio.montagem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class HamburguerDescricaoTest {

    @Test
    void descricaoIncluiPontoDaCarne() {
        Hamburguer h = new HamburguerBuilder()
                .comPao("Pao Brioche").comProteina("Hamburguer Bovino")
                .noPonto(PontoCarne.MAL_PASSADO).construir();

        assertTrue(h.getDescricao().contains("mal passado"));
    }

    @Test
    void descricaoSemPontoParaProteinaVegetal() {
        Hamburguer h = new HamburguerBuilder()
                .comPao("Pao Brioche").comProteina("Hamburguer Vegetal")
                .noPonto(PontoCarne.NAO_SE_APLICA).construir();

        assertTrue(h.getDescricao().contains("("));
        assertTrue(!h.getDescricao().contains("carne"));
    }
}
