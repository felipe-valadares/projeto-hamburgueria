package com.hamburgueria.entrega.frete;

import com.hamburgueria.pedido.Pedido;

import java.math.BigDecimal;

public interface CalculoFrete {

    BigDecimal calcular(Pedido pedido);
}
