package com.hamburgueria.producao;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FilaProducaoCompletoTest {

    private Pedido pedido() {
        ItemPedido item = new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 1);
        return new Pedido(new Cliente("A", "1", "a@e.com"), TipoEntrega.BALCAO, List.of(item));
    }

    @Test
    void filaVaziaIsEmpty() {
        FilaProducao fila = new FilaProducao();
        assertTrue(fila.isVazia());
    }

    @Test
    void iteradorLancaExcecaoAoFimDaFila() {
        FilaProducao fila = new FilaProducao();
        var it = fila.iterator();
        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
    }
}
