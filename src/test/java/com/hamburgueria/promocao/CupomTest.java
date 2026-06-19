package com.hamburgueria.promocao;

import com.hamburgueria.promocao.regra.QuantidadeMinimaItens;
import com.hamburgueria.promocao.regra.RegraE;
import com.hamburgueria.promocao.regra.SubtotalMinimo;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CupomTest {

    private Cupom cupomCombo() {
        return new Cupom("COMBO10", new BigDecimal("0.10"),
                new RegraE(new SubtotalMinimo(new BigDecimal("50.00")), new QuantidadeMinimaItens(3)));
    }

    @Test
    void aplicaQuandoTodasAsCondicoesSaoSatisfeitas() {
        ContextoPromocao contexto = new ContextoPromocao(new BigDecimal("60.00"), 4);

        assertTrue(cupomCombo().isAplicavel(contexto));
    }

    @Test
    void naoAplicaQuandoUmaCondicaoFalha() {
        ContextoPromocao poucoItens = new ContextoPromocao(new BigDecimal("60.00"), 2);
        ContextoPromocao subtotalBaixo = new ContextoPromocao(new BigDecimal("40.00"), 5);

        assertFalse(cupomCombo().isAplicavel(poucoItens));
        assertFalse(cupomCombo().isAplicavel(subtotalBaixo));
    }

    @Test
    void calculaDescontoPercentualSobreOsubtotal() {
        ContextoPromocao contexto = new ContextoPromocao(new BigDecimal("60.00"), 4);

        assertEquals(new BigDecimal("6.00"), cupomCombo().calcularDesconto(contexto));
    }

    @Test
    void descontoEhZeroQuandoNaoAplicavel() {
        ContextoPromocao contexto = new ContextoPromocao(new BigDecimal("40.00"), 1);

        assertEquals(new BigDecimal("0.00"), cupomCombo().calcularDesconto(contexto));
    }
}
