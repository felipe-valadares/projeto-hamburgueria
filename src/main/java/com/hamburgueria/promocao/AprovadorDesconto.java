package com.hamburgueria.promocao;

import java.math.BigDecimal;

public abstract class AprovadorDesconto {

    private AprovadorDesconto proximo;

    public AprovadorDesconto definirProximo(AprovadorDesconto proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public String aprovar(SolicitacaoDesconto solicitacao) {
        if (podeAprovar(solicitacao.getValor())) {
            return getCargo();
        }
        if (proximo != null) {
            return proximo.aprovar(solicitacao);
        }
        throw new DescontoNaoAutorizadoException(solicitacao.getValor());
    }

    protected abstract boolean podeAprovar(BigDecimal valor);

    protected abstract String getCargo();

    public static AprovadorDesconto cadeiaPadrao() {
        AprovadorDesconto atendente = new AprovacaoAtendente();
        atendente.definirProximo(new AprovacaoSupervisor())
                .definirProximo(new AprovacaoGerente())
                .definirProximo(new AprovacaoDono());
        return atendente;
    }
}
