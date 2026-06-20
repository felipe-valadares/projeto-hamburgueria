package com.hamburgueria.entrega;

import com.hamburgueria.entrega.frete.CalculoFrete;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;

import java.math.BigDecimal;

public class ServicoDelivery implements ServicoEntrega {

    private final CalculoFrete calculoFrete;

    public ServicoDelivery(CalculoFrete calculoFrete) {
        this.calculoFrete = calculoFrete;
    }

    @Override
    public TipoEntrega getTipo() {
        return TipoEntrega.DELIVERY;
    }

    @Override
    public int prazoEstimadoMinutos() {
        return 45;
    }

    @Override
    public boolean exigeEndereco() {
        return true;
    }

    @Override
    public BigDecimal calcularFrete(Pedido pedido) {
        return calculoFrete.calcular(pedido);
    }
}
