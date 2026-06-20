package com.hamburgueria.pagamento;

public class PagamentoInvalidoException extends RuntimeException {

    public PagamentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
