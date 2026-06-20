package com.hamburgueria.entrega.frete;

import com.hamburgueria.infraestrutura.Configuracao;
import com.hamburgueria.pedido.Pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FreteFixo implements CalculoFrete {

    @Override
    public BigDecimal calcular(Pedido pedido) {
        return Configuracao.getInstancia().getFreteBase().setScale(2, RoundingMode.HALF_UP);
    }
}
