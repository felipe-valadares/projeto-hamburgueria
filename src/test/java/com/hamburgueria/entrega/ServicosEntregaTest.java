package com.hamburgueria.entrega;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ServicosEntregaTest {

    private Pedido pedido(TipoEntrega tipo) {
        Cliente c = new Cliente("Ana", "11999990000", "ana@email.com");
        ItemPedido item = new ItemPedido(new Bebida("Bebida", new BigDecimal("8.00"), 100, 350, false), 1);
        return new Pedido(c, tipo, List.of(item));
    }

    @Test
    void retiradaTemFretZeroESemEndereco() {
        ServicoRetirada retirada = new ServicoRetirada();
        assertEquals(TipoEntrega.RETIRADA, retirada.getTipo());
        assertEquals(25, retirada.prazoEstimadoMinutos());
        assertFalse(retirada.exigeEndereco());
        assertEquals(new BigDecimal("0.00"), retirada.calcularFrete(pedido(TipoEntrega.RETIRADA)));
    }

    @Test
    void driveThruTemFreteZeroESemEndereco() {
        ServicoDriveThru driveThru = new ServicoDriveThru();
        assertEquals(TipoEntrega.DRIVE_THRU, driveThru.getTipo());
        assertEquals(15, driveThru.prazoEstimadoMinutos());
        assertFalse(driveThru.exigeEndereco());
        assertEquals(new BigDecimal("0.00"), driveThru.calcularFrete(pedido(TipoEntrega.DRIVE_THRU)));
    }

    @Test
    void balcaoTemFreteZero() {
        ServicoBalcao balcao = new ServicoBalcao();
        assertEquals(TipoEntrega.BALCAO, balcao.getTipo());
        assertEquals(new BigDecimal("0.00"), balcao.calcularFrete(pedido(TipoEntrega.BALCAO)));
    }

    @Test
    void factoryCriaRetirada() {
        ServicoEntrega s = new ServicoEntregaFactory().criar(TipoEntrega.RETIRADA);
        assertEquals(TipoEntrega.RETIRADA, s.getTipo());
    }

    @Test
    void factoryCriaDriveThru() {
        ServicoEntrega s = new ServicoEntregaFactory().criar(TipoEntrega.DRIVE_THRU);
        assertEquals(TipoEntrega.DRIVE_THRU, s.getTipo());
    }
}
