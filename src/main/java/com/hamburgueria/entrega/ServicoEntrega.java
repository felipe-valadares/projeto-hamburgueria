package com.hamburgueria.entrega;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;

import java.math.BigDecimal;

public interface ServicoEntrega {

    TipoEntrega getTipo();

    int prazoEstimadoMinutos();

    boolean exigeEndereco();

    BigDecimal calcularFrete(Pedido pedido);
}
