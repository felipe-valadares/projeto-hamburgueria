package com.hamburgueria.entrega.frete;

import com.hamburgueria.infraestrutura.Configuracao;
import com.hamburgueria.pedido.Pedido;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FreteGratisAcimaDe implements CalculoFrete {

    private final CalculoFrete base;

    public FreteGratisAcimaDe(CalculoFrete base) {
        this.base = base;
    }

    @Override
    public BigDecimal calcular(Pedido pedido) {
        BigDecimal minimo = Configuracao.getInstancia().getPedidoMinimoFreteGratis();
        if (pedido.getSubtotal().compareTo(minimo) >= 0) {
            return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        }
        return base.calcular(pedido);
    }
}
