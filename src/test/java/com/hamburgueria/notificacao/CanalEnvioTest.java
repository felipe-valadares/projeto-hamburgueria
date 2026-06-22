package com.hamburgueria.notificacao;

import com.hamburgueria.pessoa.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CanalEnvioTest {

    @Test
    void pushUsaNomeDoCliente() {
        CanalPush push = new CanalPush();
        String resultado = push.enviar(new Cliente("Ana", "11999990000", "ana@email.com"), "Teste");
        assertTrue(resultado.contains("[PUSH]"));
        assertTrue(resultado.contains("Ana"));
    }
}
