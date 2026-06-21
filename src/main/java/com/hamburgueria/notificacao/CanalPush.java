package com.hamburgueria.notificacao;

import com.hamburgueria.pessoa.Cliente;

public class CanalPush implements CanalEnvio {

    @Override
    public String enviar(Cliente cliente, String mensagem) {
        return "[PUSH] " + cliente.getNome() + ": " + mensagem;
    }
}
