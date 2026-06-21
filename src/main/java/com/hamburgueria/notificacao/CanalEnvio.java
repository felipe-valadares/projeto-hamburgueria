package com.hamburgueria.notificacao;

import com.hamburgueria.pessoa.Cliente;

public interface CanalEnvio {

    String enviar(Cliente cliente, String mensagem);
}
