package com.hamburgueria.pedido.evento;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class HistoricoStatusTest {

    @Test
    void getUltimoRetornaNullQuandoVazio() {
        HistoricoStatus h = new HistoricoStatus();
        assertNull(h.getUltimo());
    }

    @Test
    void getUltimoRetornaUltimaTransicao() {
        ItemPedido item = new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 1);
        Pedido pedido = new Pedido(new Cliente("A", "11999990000", "a@e.com"), TipoEntrega.BALCAO, List.of(item));
        HistoricoStatus h = new HistoricoStatus();
        pedido.registrarObservador(h);

        pedido.avancar();
        pedido.avancar();

        assertEquals(StatusPedido.PRONTO, h.getUltimo());
    }
}
