package com.hamburgueria.notificacao;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificacaoTest {

    private Pedido pedido() {
        Cliente cliente = new Cliente("Ana", "11999990000", "ana@email.com");
        ItemPedido item = new ItemPedido(new Bebida("Bebida", new BigDecimal("8.00"), 100, 350, false), 1);
        return new Pedido(cliente, TipoEntrega.DELIVERY, List.of(item));
    }

    @Test
    void mesmaNotificacaoFuncionaEmCanaisDiferentes() {
        Pedido pedido = pedido();

        String porSms = new NotificacaoPedidoPronto(new CanalSms()).enviar(pedido);
        String porEmail = new NotificacaoPedidoPronto(new CanalEmail()).enviar(pedido);

        assertTrue(porSms.startsWith("[SMS]"));
        assertTrue(porSms.contains("pronto"));
        assertTrue(porEmail.startsWith("[EMAIL]"));
        assertTrue(porEmail.contains("ana@email.com"));
    }

    @Test
    void mesmoCanalAtendeNotificacoesDiferentes() {
        Pedido pedido = pedido();
        CanalWhatsApp canal = new CanalWhatsApp();

        String pronto = new NotificacaoPedidoPronto(canal).enviar(pedido);
        String saiu = new NotificacaoSaiuParaEntrega(canal).enviar(pedido);

        assertTrue(pronto.contains("pronto"));
        assertTrue(saiu.contains("saiu para entrega"));
    }
}
