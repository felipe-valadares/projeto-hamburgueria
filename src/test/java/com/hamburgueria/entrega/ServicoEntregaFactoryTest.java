package com.hamburgueria.entrega;

import com.hamburgueria.pedido.TipoEntrega;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServicoEntregaFactoryTest {

    private final ServicoEntregaFactory factory = new ServicoEntregaFactory();

    @Test
    void criaServicoDeDeliveryQueExigeEndereco() {
        ServicoEntrega servico = factory.criar(TipoEntrega.DELIVERY);

        assertEquals(TipoEntrega.DELIVERY, servico.getTipo());
        assertTrue(servico.exigeEndereco());
    }

    @Test
    void criaServicoDeBalcaoSemEndereco() {
        ServicoEntrega servico = factory.criar(TipoEntrega.BALCAO);

        assertEquals(TipoEntrega.BALCAO, servico.getTipo());
        assertFalse(servico.exigeEndereco());
    }

    @Test
    void cadaCanalTemPrazoProprio() {
        assertTrue(factory.criar(TipoEntrega.DELIVERY).prazoEstimadoMinutos()
                > factory.criar(TipoEntrega.DRIVE_THRU).prazoEstimadoMinutos());
    }
}
