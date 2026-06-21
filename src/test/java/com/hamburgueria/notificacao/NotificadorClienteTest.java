package com.hamburgueria.notificacao;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificadorClienteTest {

    @Test
    void avisaOclienteAcadaMudancaRelevanteDeStatus() {
        Cliente cliente = new Cliente("Ana", "11999990000", "ana@email.com");
        ItemPedido item = new ItemPedido(new Bebida("Bebida", new BigDecimal("8.00"), 100, 350, false), 1);
        Pedido pedido = new Pedido(cliente, TipoEntrega.BALCAO, List.of(item));

        NotificadorCliente notificador = new NotificadorCliente(new CanalSms());
        pedido.registrarObservador(notificador);

        pedido.avancar();
        pedido.avancar();

        List<String> avisos = notificador.getAvisosEnviados();
        assertEquals(2, avisos.size());
        assertTrue(avisos.get(0).contains("confirmado"));
        assertTrue(avisos.get(1).contains("pronto"));
    }
}
