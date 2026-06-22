package com.hamburgueria.promocao.regra;

import com.hamburgueria.promocao.ContextoPromocao;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegraOuTest {

    @Test
    void verdadeiroSeUmaDasCondicoesEhSatisfeita() {
        RegraOu regra = new RegraOu(
                new SubtotalMinimo(new BigDecimal("100.00")),
                new QuantidadeMinimaItens(3));

        ContextoPromocao contexto = new ContextoPromocao(new BigDecimal("30.00"), 5);

        assertTrue(regra.interpretar(contexto));
    }

    @Test
    void verdadeiroSeAmbasCondicoesSaoSatisfeitas() {
        RegraOu regra = new RegraOu(
                new SubtotalMinimo(new BigDecimal("20.00")),
                new QuantidadeMinimaItens(2));

        ContextoPromocao contexto = new ContextoPromocao(new BigDecimal("50.00"), 4);

        assertTrue(regra.interpretar(contexto));
    }

    @Test
    void falsoSeNenhumaCondicaoEhSatisfeita() {
        RegraOu regra = new RegraOu(
                new SubtotalMinimo(new BigDecimal("100.00")),
                new QuantidadeMinimaItens(10));

        ContextoPromocao contexto = new ContextoPromocao(new BigDecimal("30.00"), 2);

        assertFalse(regra.interpretar(contexto));
    }
}
