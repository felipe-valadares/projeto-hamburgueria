package com.hamburgueria.entrega.frete;

import com.hamburgueria.pedido.Pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FreteCortesia implements CalculoFrete {

    @Override
    public BigDecimal calcular(Pedido pedido) {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}
