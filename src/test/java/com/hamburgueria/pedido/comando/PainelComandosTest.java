package com.hamburgueria.pedido.comando;

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

class PainelComandosTest {

    private Pedido novoPedido() {
        ItemPedido item = new ItemPedido(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false), 1);
        return new Pedido(new Cliente("Ana", "11999990000", "ana@email.com"), TipoEntrega.BALCAO, List.of(item));
    }

    @Test
    void processaComandosEnfileiradosNaOrdem() {
        Pedido pedido = novoPedido();
        PainelComandos painel = new PainelComandos();
        painel.enfileirar(new AvancarPedidoComando(pedido));
        painel.enfileirar(new AvancarPedidoComando(pedido));

        painel.processarFila();

        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
        assertEquals(0, painel.pendentes());
    }

    @Test
    void desfazerUltimoReverteAtransicao() {
        Pedido pedido = novoPedido();
        PainelComandos painel = new PainelComandos();

        painel.executar(new AvancarPedidoComando(pedido));
        assertEquals(StatusPedido.EM_PREPARO, pedido.getStatus());

        painel.desfazerUltimo();

        assertEquals(StatusPedido.RECEBIDO, pedido.getStatus());
    }

    @Test
    void desfazerCancelamentoRestauraStatusAnterior() {
        Pedido pedido = novoPedido();
        PainelComandos painel = new PainelComandos();
        painel.executar(new AvancarPedidoComando(pedido));

        painel.executar(new CancelarPedidoComando(pedido));
        assertEquals(StatusPedido.CANCELADO, pedido.getStatus());

        painel.desfazerUltimo();

        assertEquals(StatusPedido.EM_PREPARO, pedido.getStatus());
    }
}
