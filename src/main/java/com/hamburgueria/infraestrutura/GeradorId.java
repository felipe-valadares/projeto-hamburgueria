package com.hamburgueria.infraestrutura;

import java.util.concurrent.atomic.AtomicLong;

public final class GeradorId {

    private static final AtomicLong SEQUENCIA = new AtomicLong(1000);

    private GeradorId() {
    }

    public static long proximo() {
        return SEQUENCIA.incrementAndGet();
    }
}
