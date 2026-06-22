package com.hamburgueria.relatorio;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelatorioGerencialProxyTest {

    private RelatorioFaturamento relatorioReal() {
        return new RelatorioFaturamento(List.of(new BigDecimal("50.00"), new BigDecimal("30.00")));
    }

    @Test
    void gerenteAcessaORelatorio() {
        RelatorioGerencialProxy proxy = new RelatorioGerencialProxy("Gerente", relatorioReal());

        assertTrue(proxy.gerar().contains("80.00"));
    }

    @Test
    void atendenteNaoTemPermissao() {
        RelatorioGerencialProxy proxy = new RelatorioGerencialProxy("Atendente", relatorioReal());

        assertThrows(AcessoNegadoException.class, proxy::gerar);
    }

    @Test
    void relatorioCaroEhGeradoUmaUnicaVezEReaproveitado() {
        RelatorioFaturamento real = relatorioReal();
        RelatorioGerencialProxy proxy = new RelatorioGerencialProxy("Dono", real);

        proxy.gerar();
        proxy.gerar();
        proxy.gerar();

        assertEquals(1, real.getExecucoes());
    }
}
