package com.hamburgueria.pagamento.gateway;

public class RetornoTransacao {

    private final String codigoRetorno;
    private final String idTransacao;

    public RetornoTransacao(String codigoRetorno, String idTransacao) {
        this.codigoRetorno = codigoRetorno;
        this.idTransacao = idTransacao;
    }

    public String getCodigoRetorno() {
        return codigoRetorno;
    }

    public String getIdTransacao() {
        return idTransacao;
    }
}
