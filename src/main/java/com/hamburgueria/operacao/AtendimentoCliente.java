package com.hamburgueria.operacao;

import com.hamburgueria.notificacao.CanalEnvio;
import com.hamburgueria.notificacao.NotificacaoPedidoPronto;
import com.hamburgueria.notificacao.NotificacaoSaiuParaEntrega;
import com.hamburgueria.pedido.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtendimentoCliente extends SetorOperacao {

    private final CanalEnvio canal;
    private final List<String> avisos = new ArrayList<>();

    public AtendimentoCliente(CanalEnvio canal) {
        this.canal = canal;
    }

    public void avisarPronto(Pedido pedido) {
        avisos.add(new NotificacaoPedidoPronto(canal).enviar(pedido));
    }

    public void avisarSaiuParaEntrega(Pedido pedido) {
        avisos.add(new NotificacaoSaiuParaEntrega(canal).enviar(pedido));
    }

    public List<String> getAvisos() {
        return Collections.unmodifiableList(avisos);
    }
}
