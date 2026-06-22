package com.hamburgueria.entrega.frete;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.infraestrutura.Configuracao;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FreteFixoTest {

    @Test
    void retornaFreteBaseDaConfiguracao() {
        Configuracao.getInstancia().setFreteBase(new BigDecimal("6.00"));
        Cliente c = new Cliente("Ana", "11999990000", "ana@email.com");
        ItemPedido item = new ItemPedido(new Bebida("Bebida", new BigDecimal("8.00"), 100, 350, false), 1);
        Pedido pedido = new Pedido(c, TipoEntrega.DELIVERY, List.of(item));

        assertEquals(new BigDecimal("6.00"), new FreteFixo().calcular(pedido));
    }
}
