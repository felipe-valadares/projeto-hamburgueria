package com.hamburgueria.notificacao;

import com.hamburgueria.pessoa.Cliente;

public class CanalEmail implements CanalEnvio {

    @Override
    public String enviar(Cliente cliente, String mensagem) {
        return "[EMAIL] " + cliente.getEmail() + ": " + mensagem;
    }
}
