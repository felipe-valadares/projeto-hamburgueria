package com.hamburgueria.cardapio;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CategoriaCompletoTest {

    @Test
    void removerItemReduceOPreco() {
        Categoria bebidas = new Categoria("Bebidas");
        Bebida refri = new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false);
        Bebida suco = new Bebida("Suco", new BigDecimal("10.00"), 90, 400, false);
        bebidas.adicionar(refri).adicionar(suco);

        bebidas.remover(suco);

        assertEquals(new BigDecimal("8.00"), bebidas.getPreco());
        assertEquals(1, bebidas.getItens().size());
    }

    @Test
    void descricaoListaOsItens() {
        Categoria cat = new Categoria("Lanches");
        cat.adicionar(new Acompanhamento("Batata Frita", new BigDecimal("12.00"), 400));

        String descricao = cat.getDescricao();

        assertTrue(descricao.startsWith("Lanches"));
        assertTrue(descricao.contains("Batata Frita"));
    }

    @Test
    void getNomeRetornaOnomeDaCategoria() {
        Categoria cat = new Categoria("Sobremesas");
        assertEquals("Sobremesas", cat.getNome());
    }
}
