package com.hamburgueria.operacao;

import com.hamburgueria.pedido.Pedido;

public interface MediadorOperacao {

    void notificar(SetorOperacao origem, Pedido pedido, EventoOperacao evento);
}
