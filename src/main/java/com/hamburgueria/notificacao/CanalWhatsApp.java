package com.hamburgueria.notificacao;

import com.hamburgueria.pessoa.Cliente;

public class CanalWhatsApp implements CanalEnvio {

    @Override
    public String enviar(Cliente cliente, String mensagem) {
        return "[WHATSAPP] " + cliente.getTelefone() + ": " + mensagem;
    }
}
