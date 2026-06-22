package com.hamburgueria.operacao;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pagamento.StatusPagamento;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResumoAtendimentoTest {

    @Test
    void expoeAtributosCorretamente() {
        ItemPedido item = new ItemPedido(new Bebida("B", new BigDecimal("8.00"), 100, 350, false), 1);
        Pedido pedido = new Pedido(new Cliente("A", "1", "a@e.com"), TipoEntrega.BALCAO, List.of(item));
        ResumoAtendimento r = new ResumoAtendimento(pedido, new BigDecimal("5.00"),
                new BigDecimal("13.00"), StatusPagamento.PAGO, List.of("aviso"));

        assertEquals(pedido.getId(), r.getPedidoId());
        assertEquals(new BigDecimal("5.00"), r.getFrete());
        assertEquals(new BigDecimal("13.00"), r.getTotal());
        assertEquals(StatusPedido.RECEBIDO, r.getStatusPedido());
        assertEquals(1, r.getAvisos().size());
    }
}
