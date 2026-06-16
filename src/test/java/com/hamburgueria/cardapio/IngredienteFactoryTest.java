package com.hamburgueria.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IngredienteFactoryTest {

    @Test
    void compartilhaAmesmaInstanciaParaOmesmoIngrediente() {
        Ingrediente primeiro = IngredienteFactory.obter("Bacon");
        Ingrediente segundo = IngredienteFactory.obter("bacon");

        assertSame(primeiro, segundo);
    }

    @Test
    void naoDuplicaIngredientesAoConsultarOcatalogo() {
        int antes = IngredienteFactory.quantidadeCadastrada();

        IngredienteFactory.obter("Bacon");
        IngredienteFactory.obter("Alface");

        assertEquals(antes, IngredienteFactory.quantidadeCadastrada());
    }

    @Test
    void rejeitaIngredienteForaDoCatalogo() {
        assertThrows(IngredienteIndisponivelException.class,
                () -> IngredienteFactory.obter("Trufa Branca"));
    }

    @Test
    void exponeAtributosNutricionais() {
        Ingrediente bacon = IngredienteFactory.obter("Bacon");

        assertTrue(bacon.getCalorias() > 0);
        assertFalse(bacon.isVegano());
    }
}
