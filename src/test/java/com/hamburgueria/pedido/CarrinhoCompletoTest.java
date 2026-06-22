package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CarrinhoCompletoTest {

    private ItemPedido item() {
        return new ItemPedido(new Bebida("Bebida", new BigDecimal("8.00"), 100, 350, false), 1);
    }

    @Test
    void removerItemDoCarrinho() {
        Carrinho carrinho = new Carrinho();
        ItemPedido i = item();
        carrinho.adicionar(i);
        carrinho.remover(i);
        assertTrue(carrinho.isVazio());
    }

    @Test
    void limparEsvaziaOcarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(item());
        carrinho.adicionar(item());
        carrinho.limpar();
        assertTrue(carrinho.isVazio());
        assertEquals(0, carrinho.quantidadeItens());
    }

    @Test
    void isVazioRetornaFalseComItens() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(item());
        assertFalse(carrinho.isVazio());
    }
}
