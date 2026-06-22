package com.hamburgueria.pedido.estado;

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
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstadosPedidoCompletoTest {

    private Pedido pedido() {
        ItemPedido item = new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 1);
        return new Pedido(new Cliente("Ana", "11999990000", "ana@email.com"), TipoEntrega.BALCAO, List.of(item));
    }

    @Test
    void pedidoRecebidoTemStatusCorreto() {
        assertEquals(StatusPedido.RECEBIDO, new PedidoRecebido().getStatus());
    }

    @Test
    void pedidoCanceladoNaoPermiteAvancar() {
        Pedido p = pedido();
        p.cancelar();
        assertThrows(TransicaoInvalidaException.class, p::avancar);
    }

    @Test
    void pedidoCanceladoNaoPermiteCancelarNovamente() {
        Pedido p = pedido();
        p.cancelar();
        assertThrows(TransicaoInvalidaException.class, p::cancelar);
    }

    @Test
    void pedidoEntregueNaoPermiteCancelar() {
        Pedido p = pedido();
        p.avancar(); // EM_PREPARO
        p.avancar(); // PRONTO
        p.avancar(); // ENTREGUE
        assertThrows(TransicaoInvalidaException.class, p::cancelar);
    }
}
