package com.hamburgueria.notificacao;

import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.evento.ObservadorPedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotificadorCliente implements ObservadorPedido {

    private final CanalEnvio canal;
    private final List<String> avisosEnviados = new ArrayList<>();

    public NotificadorCliente(CanalEnvio canal) {
        this.canal = canal;
    }

    @Override
    public void statusAtualizado(Pedido pedido) {
        Notificacao notificacao = selecionar(pedido);
        if (notificacao != null) {
            avisosEnviados.add(notificacao.enviar(pedido));
        }
    }

    public List<String> getAvisosEnviados() {
        return Collections.unmodifiableList(avisosEnviados);
    }

    private Notificacao selecionar(Pedido pedido) {
        switch (pedido.getStatus()) {
            case EM_PREPARO:
                return new NotificacaoPedidoConfirmado(canal);
            case PRONTO:
                return new NotificacaoPedidoPronto(canal);
            case SAIU_PARA_ENTREGA:
                return new NotificacaoSaiuParaEntrega(canal);
            default:
                return null;
        }
    }
}
