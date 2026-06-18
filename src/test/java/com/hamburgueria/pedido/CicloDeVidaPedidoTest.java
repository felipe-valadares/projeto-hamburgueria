package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.estado.TransicaoInvalidaException;
import com.hamburgueria.pedido.evento.HistoricoStatus;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CicloDeVidaPedidoTest {

    private Pedido novoPedido(TipoEntrega tipo) {
        ItemPedido item = new ItemPedido(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false), 1);
        return new Pedido(new Cliente("Ana", "11999990000", "ana@email.com"), tipo, List.of(item));
    }

    @Test
    void comecaComoRecebido() {
        assertEquals(StatusPedido.RECEBIDO, novoPedido(TipoEntrega.BALCAO).getStatus());
    }

    @Test
    void fluxoDeBalcaoVaiDiretoDeProntoParaEntregue() {
        Pedido pedido = novoPedido(TipoEntrega.BALCAO);

        pedido.avancar();
        assertEquals(StatusPedido.EM_PREPARO, pedido.getStatus());
        pedido.avancar();
        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
        pedido.avancar();
        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());
    }

    @Test
    void fluxoDeDeliveryPassaPorSaiuParaEntrega() {
        Pedido pedido = novoPedido(TipoEntrega.DELIVERY);

        pedido.avancar();
        pedido.avancar();
        pedido.avancar();
        assertEquals(StatusPedido.SAIU_PARA_ENTREGA, pedido.getStatus());
        pedido.avancar();
        assertEquals(StatusPedido.ENTREGUE, pedido.getStatus());
    }

    @Test
    void naoPermiteAvancarPedidoEntregue() {
        Pedido pedido = novoPedido(TipoEntrega.BALCAO);
        pedido.avancar();
        pedido.avancar();
        pedido.avancar();

        assertThrows(TransicaoInvalidaException.class, pedido::avancar);
    }

    @Test
    void naoPermiteCancelarPedidoEmRota() {
        Pedido pedido = novoPedido(TipoEntrega.DELIVERY);
        pedido.avancar();
        pedido.avancar();
        pedido.avancar();

        assertThrows(TransicaoInvalidaException.class, pedido::cancelar);
    }

    @Test
    void observadorRecebeCadaTransicao() {
        Pedido pedido = novoPedido(TipoEntrega.BALCAO);
        HistoricoStatus historico = new HistoricoStatus();
        pedido.registrarObservador(historico);

        pedido.avancar();
        pedido.avancar();
        pedido.cancelar();

        assertEquals(List.of(StatusPedido.EM_PREPARO, StatusPedido.PRONTO, StatusPedido.CANCELADO),
                historico.getTransicoes());
    }
}
