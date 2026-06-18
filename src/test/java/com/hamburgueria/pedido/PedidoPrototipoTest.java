package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.montagem.HamburguerBuilder;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PedidoPrototipoTest {

    private Pedido pedidoBase() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new ItemPedido(new HamburguerBuilder()
                .comPao("Pao Brioche").comProteina("Hamburguer Bovino").construir(), 1));
        carrinho.adicionar(new ItemPedido(
                new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false), 2));
        return carrinho.fecharPedido(new Cliente("Bruno", "1198887777", "bruno@email.com"),
                TipoEntrega.DELIVERY);
    }

    @Test
    void repetirGeraNovoPedidoComMesmosItens() {
        Pedido original = pedidoBase();
        Pedido repetido = original.repetir();

        assertNotEquals(original.getId(), repetido.getId());
        assertEquals(original.getSubtotal(), repetido.getSubtotal());
        assertEquals(original.quantidadeItens(), repetido.quantidadeItens());
    }

    @Test
    void alterarRepetidoNaoAfetaOriginal() {
        Pedido original = pedidoBase();
        Pedido repetido = original.repetir();

        repetido.getItens().get(0).setQuantidade(9);

        assertNotEquals(original.getItens().get(0).getQuantidade(),
                repetido.getItens().get(0).getQuantidade());
    }
}
