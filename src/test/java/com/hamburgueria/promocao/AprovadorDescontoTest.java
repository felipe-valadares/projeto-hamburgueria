package com.hamburgueria.promocao;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AprovadorDescontoTest {

    private String aprovar(String valor) {
        return AprovadorDesconto.cadeiaPadrao()
                .aprovar(new SolicitacaoDesconto(new BigDecimal(valor), "fidelidade"));
    }

    @Test
    void descontoPequenoEhResolvidoPeloAtendente() {
        assertEquals("Atendente", aprovar("8.00"));
    }

    @Test
    void descontoEscalaParaOsupervisor() {
        assertEquals("Supervisor", aprovar("25.00"));
    }

    @Test
    void descontoEscalaParaOgerente() {
        assertEquals("Gerente", aprovar("70.00"));
    }

    @Test
    void descontoAltoChegaAoDono() {
        assertEquals("Dono", aprovar("150.00"));
    }

    @Test
    void descontoAcimaDoLimiteEhRejeitado() {
        assertThrows(DescontoNaoAutorizadoException.class, () -> aprovar("500.00"));
    }
}
