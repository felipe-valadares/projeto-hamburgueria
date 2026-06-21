package com.hamburgueria.operacao;

import com.hamburgueria.notificacao.CanalEnvio;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;

import java.util.List;

public class CentralOperacoes implements MediadorOperacao {

    private final Cozinha cozinha = new Cozinha();
    private final Expedicao expedicao = new Expedicao();
    private final AtendimentoCliente atendimento;

    public CentralOperacoes(CanalEnvio canal) {
        this.atendimento = new AtendimentoCliente(canal);
        cozinha.definirMediador(this);
        expedicao.definirMediador(this);
        atendimento.definirMediador(this);
    }

    public void processar(Pedido pedido) {
        cozinha.preparar(pedido);
    }

    @Override
    public void notificar(SetorOperacao origem, Pedido pedido, EventoOperacao evento) {
        switch (evento) {
            case PEDIDO_PRONTO:
                atendimento.avisarPronto(pedido);
                if (pedido.getTipoEntrega() == TipoEntrega.DELIVERY) {
                    expedicao.despachar(pedido);
                }
                break;
            case PEDIDO_DESPACHADO:
                atendimento.avisarSaiuParaEntrega(pedido);
                break;
            default:
                break;
        }
    }

    public List<String> getAvisos() {
        return atendimento.getAvisos();
    }
}
