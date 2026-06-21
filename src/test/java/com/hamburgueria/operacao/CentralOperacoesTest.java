package com.hamburgueria.operacao;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.notificacao.CanalSms;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CentralOperacoesTest {

    private Pedido pedido(TipoEntrega tipo) {
        Cliente cliente = new Cliente("Ana", "11999990000", "ana@email.com");
        ItemPedido item = new ItemPedido(new Bebida("Bebida", new BigDecimal("8.00"), 100, 350, false), 1);
        return new Pedido(cliente, tipo, List.of(item));
    }

    @Test
    void pedidoDeBalcaoEhPreparadoEAvisadoSemDespacho() {
        CentralOperacoes central = new CentralOperacoes(new CanalSms());
        Pedido pedido = pedido(TipoEntrega.BALCAO);

        central.processar(pedido);

        assertEquals(StatusPedido.PRONTO, pedido.getStatus());
        assertEquals(1, central.getAvisos().size());
        assertTrue(central.getAvisos().get(0).contains("pronto"));
    }

    @Test
    void pedidoDeDeliveryEhDespachadoEgeraDoisAvisos() {
        CentralOperacoes central = new CentralOperacoes(new CanalSms());
        Pedido pedido = pedido(TipoEntrega.DELIVERY);

        central.processar(pedido);

        assertEquals(StatusPedido.SAIU_PARA_ENTREGA, pedido.getStatus());
        assertEquals(2, central.getAvisos().size());
        assertTrue(central.getAvisos().get(1).contains("saiu para entrega"));
    }
}
