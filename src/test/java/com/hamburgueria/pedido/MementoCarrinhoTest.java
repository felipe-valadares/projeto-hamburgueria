package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.Bebida;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MementoCarrinhoTest {

    private ItemPedido bebida(String nome) {
        return new ItemPedido(new Bebida(nome, new BigDecimal("8.00"), 140, 350, false), 1);
    }

    @Test
    void desfazRetornaAoEstadoSalvo() {
        Carrinho carrinho = new Carrinho();
        HistoricoCarrinho historico = new HistoricoCarrinho();
        carrinho.adicionar(bebida("Refrigerante"));

        historico.salvar(carrinho);
        carrinho.adicionar(bebida("Suco"));
        carrinho.adicionar(bebida("Agua"));
        assertEquals(3, carrinho.getItens().size());

        historico.desfazer(carrinho);

        assertEquals(1, carrinho.getItens().size());
    }

    @Test
    void desfazerSucessivamenteRecuperaEstadosAnteriores() {
        Carrinho carrinho = new Carrinho();
        HistoricoCarrinho historico = new HistoricoCarrinho();

        historico.salvar(carrinho);
        carrinho.adicionar(bebida("Refrigerante"));
        historico.salvar(carrinho);
        carrinho.adicionar(bebida("Suco"));

        historico.desfazer(carrinho);
        assertEquals(1, carrinho.getItens().size());
        historico.desfazer(carrinho);
        assertEquals(0, carrinho.getItens().size());
    }
}
