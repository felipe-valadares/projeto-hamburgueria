package com.hamburgueria.pagamento.gateway;

import com.hamburgueria.pagamento.ComprovantePagamento;
import com.hamburgueria.pagamento.MeioPagamento;

import java.math.BigDecimal;

public class GatewayPagamentoAdapter implements MeioPagamento {

    private final GatewayPagamentosExterno gateway;
    private final String bandeira;
    private final String numeroCartao;

    public GatewayPagamentoAdapter(GatewayPagamentosExterno gateway, String bandeira, String numeroCartao) {
        this.gateway = gateway;
        this.bandeira = bandeira;
        this.numeroCartao = numeroCartao;
    }

    @Override
    public ComprovantePagamento cobrar(BigDecimal valor) {
        long centavos = valor.movePointRight(2).longValueExact();
        RetornoTransacao retorno = gateway.autorizar(bandeira, numeroCartao, centavos);
        boolean aprovado = "APPROVED".equals(retorno.getCodigoRetorno());
        return new ComprovantePagamento(aprovado, retorno.getIdTransacao(), valor, "Cartao " + bandeira);
    }
}
