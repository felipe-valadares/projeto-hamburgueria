package com.hamburgueria.cardapio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IngredienteTest {

    @Test
    void igualPorNomeCaseInsensitive() {
        Ingrediente a = IngredienteFactory.obter("Bacon");
        Ingrediente b = IngredienteFactory.obter("bacon");
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void diferenteDeNulo() {
        Ingrediente a = IngredienteFactory.obter("Alface");
        assertFalse(a.equals(null));
    }

    @Test
    void diferenteDeOutroTipo() {
        Ingrediente a = IngredienteFactory.obter("Alface");
        assertFalse(a.equals("Alface"));
    }

    @Test
    void igualASiMesmo() {
        Ingrediente a = IngredienteFactory.obter("Bacon");
        assertTrue(a.equals(a));
    }

    @Test
    void ingredientesDiferentesNaoSaoIguais() {
        assertNotEquals(IngredienteFactory.obter("Bacon"), IngredienteFactory.obter("Alface"));
    }

    @Test
    void toStringRetornaONome() {
        assertEquals("Bacon", IngredienteFactory.obter("Bacon").toString());
    }
}
