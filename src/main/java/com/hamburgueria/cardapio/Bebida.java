package com.hamburgueria.cardapio;

import java.math.BigDecimal;

public class Bebida extends Produto {

    private final int mililitros;
    private final boolean alcoolica;

    public Bebida(String nome, BigDecimal preco, int calorias, int mililitros, boolean alcoolica) {
        super(nome, preco, calorias);
        this.mililitros = mililitros;
        this.alcoolica = alcoolica;
    }

    public int getMililitros() {
        return mililitros;
    }

    public boolean isAlcoolica() {
        return alcoolica;
    }

    @Override
    public String getDescricao() {
        return getNome() + " " + mililitros + "ml";
    }
}
