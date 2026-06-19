package com.hamburgueria.pedido.comando;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class PainelComandos {

    private final Queue<Comando> fila = new ArrayDeque<>();
    private final Deque<Comando> executados = new ArrayDeque<>();

    public void enfileirar(Comando comando) {
        fila.add(comando);
    }

    public void executar(Comando comando) {
        comando.executar();
        executados.push(comando);
    }

    public void processarFila() {
        List<Comando> pendentes = new ArrayList<>(fila);
        fila.clear();
        for (Comando comando : pendentes) {
            executar(comando);
        }
    }

    public void desfazerUltimo() {
        if (!executados.isEmpty()) {
            executados.pop().desfazer();
        }
    }

    public int pendentes() {
        return fila.size();
    }
}
