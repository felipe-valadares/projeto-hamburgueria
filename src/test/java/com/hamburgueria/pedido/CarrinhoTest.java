package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.montagem.HamburguerBuilder;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarrinhoTest {

    private ItemPedido lanche() {
        return new ItemPedido(new HamburguerBuilder()
                .comPao("Pao Brioche").comProteina("Hamburguer Bovino").construir(), 2);
    }

    private ItemPedido bebida() {
        return new ItemPedido(new Bebida("Refrigerante", new BigDecimal("8.00"), 140, 350, false), 1);
    }

    @Test
    void somaOsubtotalConsiderandoQuantidades() {
        Carrinho carrinho = new Carrinho();
        ItemPedido lanche = lanche();
        carrinho.adicionar(lanche);
        carrinho.adicionar(bebida());

        BigDecimal esperado = lanche.getSubtotal().add(new BigDecimal("8.00"));

        assertEquals(esperado, carrinho.getSubtotal());
        assertEquals(3, carrinho.quantidadeItens());
    }

    @Test
    void naoFechaPedidoComCarrinhoVazio() {
        Carrinho carrinho = new Carrinho();
        Cliente cliente = new Cliente("Ana", "1199990000", "ana@email.com");

        assertThrows(CarrinhoVazioException.class,
                () -> carrinho.fecharPedido(cliente, TipoEntrega.BALCAO));
    }

    @Test
    void pedidoPreservaOsubtotalDoCarrinho() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(lanche());
        carrinho.adicionar(bebida());
        Cliente cliente = new Cliente("Ana", "1199990000", "ana@email.com");

        Pedido pedido = carrinho.fecharPedido(cliente, TipoEntrega.DELIVERY);

        assertEquals(carrinho.getSubtotal(), pedido.getSubtotal());
    }
}
