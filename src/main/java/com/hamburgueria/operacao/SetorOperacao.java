package com.hamburgueria.operacao;

public abstract class SetorOperacao {

    protected MediadorOperacao mediador;

    public void definirMediador(MediadorOperacao mediador) {
        this.mediador = mediador;
    }
}
