package com.hamburgueria.notificacao;

import com.hamburgueria.pessoa.Cliente;

public class CanalSms implements CanalEnvio {

    @Override
    public String enviar(Cliente cliente, String mensagem) {
        return "[SMS] " + cliente.getTelefone() + ": " + mensagem;
    }
}
