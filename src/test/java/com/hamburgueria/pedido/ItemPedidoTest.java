package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemPedidoTest {

    @Test
    void quantidadeZeroNaoEhPermitida() {
        assertThrows(IllegalArgumentException.class,
                () -> new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 0));
    }

    @Test
    void setQuantidadeNegativaNaoEhPermitida() {
        ItemPedido item = new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 1);
        assertThrows(IllegalArgumentException.class, () -> item.setQuantidade(-1));
    }

    @Test
    void copiarPreservaQuantidadeEproduto() {
        Bebida b = new Bebida("B", new BigDecimal("8.00"), 100, 350, false);
        ItemPedido original = new ItemPedido(b, 3);
        ItemPedido copia = original.copiar();
        assertEquals(3, copia.getQuantidade());
        assertEquals(original.getSubtotal(), copia.getSubtotal());
    }
}
