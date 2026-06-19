package com.hamburgueria.producao;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilaProducaoTest {

    private Pedido pedido(TipoEntrega tipo) {
        ItemPedido item = new ItemPedido(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false), 1);
        return new Pedido(new Cliente("Cliente", "11999990000", "c@email.com"), tipo, List.of(item));
    }

    @Test
    void atendePedidosPresenciaisAntesDosDeDelivery() {
        FilaProducao fila = new FilaProducao();
        Pedido delivery = pedido(TipoEntrega.DELIVERY);
        Pedido balcao = pedido(TipoEntrega.BALCAO);
        fila.inserir(delivery);
        fila.inserir(balcao);

        List<TipoEntrega> ordem = new ArrayList<>();
        for (Pedido pedido : fila) {
            ordem.add(pedido.getTipoEntrega());
        }

        assertEquals(List.of(TipoEntrega.BALCAO, TipoEntrega.DELIVERY), ordem);
    }

    @Test
    void percorreTodosOsPedidosDaFila() {
        FilaProducao fila = new FilaProducao();
        fila.inserir(pedido(TipoEntrega.DELIVERY));
        fila.inserir(pedido(TipoEntrega.RETIRADA));
        fila.inserir(pedido(TipoEntrega.BALCAO));

        int contador = 0;
        for (Pedido ignorado : fila) {
            contador++;
        }

        assertEquals(3, contador);
    }
}
