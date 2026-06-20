package com.hamburgueria.entrega;

import com.hamburgueria.entrega.frete.CalculoFrete;
import com.hamburgueria.entrega.frete.FreteCortesia;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;

import java.math.BigDecimal;

public class ServicoDriveThru implements ServicoEntrega {

    private final CalculoFrete calculoFrete = new FreteCortesia();

    @Override
    public TipoEntrega getTipo() {
        return TipoEntrega.DRIVE_THRU;
    }

    @Override
    public int prazoEstimadoMinutos() {
        return 15;
    }

    @Override
    public boolean exigeEndereco() {
        return false;
    }

    @Override
    public BigDecimal calcularFrete(Pedido pedido) {
        return calculoFrete.calcular(pedido);
    }
}
