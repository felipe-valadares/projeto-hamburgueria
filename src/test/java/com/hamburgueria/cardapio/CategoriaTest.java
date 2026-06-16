package com.hamburgueria.cardapio;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoriaTest {

    @Test
    void somaOprecoDosItensDaCategoria() {
        Categoria bebidas = new Categoria("Bebidas");
        bebidas.adicionar(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false));
        bebidas.adicionar(new Bebida("Suco Natural", new BigDecimal("10.00"), 90, 400, false));

        assertEquals(new BigDecimal("18.00"), bebidas.getPreco());
    }

    @Test
    void somaPrecosDeFormaRecursivaEmSubcategorias() {
        Categoria cardapio = new Categoria("Cardapio");
        Categoria bebidas = new Categoria("Bebidas");
        Categoria sobremesas = new Categoria("Sobremesas");

        bebidas.adicionar(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false));
        sobremesas.adicionar(new Sobremesa("Milkshake", new BigDecimal("15.00"), 320));

        cardapio.adicionar(bebidas).adicionar(sobremesas);

        assertEquals(new BigDecimal("23.00"), cardapio.getPreco());
    }

    @Test
    void somaCaloriasDeFormaRecursiva() {
        Categoria combo = new Categoria("Combo");
        combo.adicionar(new Acompanhamento("Batata Frita", new BigDecimal("12.00"), 400));
        combo.adicionar(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false));

        assertEquals(540, combo.getCalorias());
    }
}
