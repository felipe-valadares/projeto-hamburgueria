package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.IngredienteFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HamburguerBuilderTest {

    @Test
    void montaHamburguerSomandoOprecoDosIngredientes() {
        Hamburguer hamburguer = new HamburguerBuilder()
                .comNome("Classico")
                .comPao("Pao Brioche")
                .comProteina("Hamburguer Bovino")
                .adicionar("Queijo Prato")
                .noPonto(PontoCarne.AO_PONTO)
                .construir();

        BigDecimal esperado = IngredienteFactory.obter("Pao Brioche").getPreco()
                .add(IngredienteFactory.obter("Hamburguer Bovino").getPreco())
                .add(IngredienteFactory.obter("Queijo Prato").getPreco());

        assertEquals(esperado, hamburguer.getPreco());
        assertEquals(PontoCarne.AO_PONTO, hamburguer.getPonto());
    }

    @Test
    void compartilhaIngredientesEntreVariosHamburgueres() {
        Hamburguer primeiro = new HamburguerBuilder()
                .comPao("Pao Brioche").comProteina("Hamburguer Bovino").construir();
        Hamburguer segundo = new HamburguerBuilder()
                .comPao("Pao Brioche").comProteina("Hamburguer Bovino").construir();

        assertTrue(primeiro.getIngredientes().get(0) == segundo.getIngredientes().get(0));
    }

    @Test
    void exigePaoNaMontagem() {
        HamburguerBuilder builder = new HamburguerBuilder().comProteina("Hamburguer Bovino");

        assertThrows(MontagemInvalidaException.class, builder::construir);
    }

    @Test
    void exigeProteinaNaMontagem() {
        HamburguerBuilder builder = new HamburguerBuilder().comPao("Pao Brioche");

        assertThrows(MontagemInvalidaException.class, builder::construir);
    }
}
