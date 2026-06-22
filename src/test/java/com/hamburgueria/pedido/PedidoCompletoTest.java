package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PedidoCompletoTest {

    private Pedido pedido() {
        ItemPedido item = new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 1);
        return new Pedido(new Cliente("Ana", "11999990000", "ana@email.com"), TipoEntrega.BALCAO, List.of(item));
    }

    @Test
    void momentoEhRegistradoNaCriacao() {
        assertNotNull(pedido().getMomento());
    }

    @Test
    void statusFinalReconhecido() {
        assertTrue(StatusPedido.ENTREGUE.isFinal());
        assertTrue(StatusPedido.CANCELADO.isFinal());
        assertFalse(StatusPedido.RECEBIDO.isFinal());
        assertFalse(StatusPedido.EM_PREPARO.isFinal());
    }

    @Test
    void statusTemDescricao() {
        assertNotNull(StatusPedido.PRONTO.getDescricao());
        assertTrue(StatusPedido.PRONTO.getDescricao().length() > 0);
    }
}
