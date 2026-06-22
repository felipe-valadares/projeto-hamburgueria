package com.hamburgueria.pedido;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistoricoCarrinhoCompletoTest {

    @Test
    void podeDesfazerRetornaFalseQuandoVazio() {
        HistoricoCarrinho h = new HistoricoCarrinho();
        assertFalse(h.podeDesfazer());
    }

    @Test
    void podeDesfazerRetornaTrueAposSalvar() {
        HistoricoCarrinho h = new HistoricoCarrinho();
        Carrinho c = new Carrinho();
        h.salvar(c);
        assertTrue(h.podeDesfazer());
    }

    @Test
    void desfazerEmHistoricoVazioNaoFazNada() {
        HistoricoCarrinho h = new HistoricoCarrinho();
        Carrinho c = new Carrinho();
        h.desfazer(c);
        assertTrue(c.isVazio());
    }
}
