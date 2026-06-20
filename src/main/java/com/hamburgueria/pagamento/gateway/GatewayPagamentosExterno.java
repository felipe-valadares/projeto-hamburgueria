package com.hamburgueria.pagamento.gateway;

public class GatewayPagamentosExterno {

    public RetornoTransacao autorizar(String bandeira, String numeroCartao, long valorEmCentavos) {
        boolean aprovado = valorEmCentavos > 0 && !numeroCartao.endsWith("0");
        String codigo = aprovado ? "APPROVED" : "DECLINED";
        String idTransacao = "TX-" + bandeira.toUpperCase() + "-" + valorEmCentavos;
        return new RetornoTransacao(codigo, idTransacao);
    }
}
