package com.hamburgueria.entrega.frete;

import com.hamburgueria.infraestrutura.Configuracao;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pessoa.Endereco;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FretePorDistancia implements CalculoFrete {

    @Override
    public BigDecimal calcular(Pedido pedido) {
        Configuracao config = Configuracao.getInstancia();
        Endereco endereco = pedido.getCliente().getEndereco();
        BigDecimal distancia = BigDecimal.valueOf(endereco.getDistanciaKm());
        return config.getFreteBase()
                .add(config.getValorPorQuilometro().multiply(distancia))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
