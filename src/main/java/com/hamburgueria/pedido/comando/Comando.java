package com.hamburgueria.pedido.comando;

public interface Comando {

    void executar();

    void desfazer();
}
