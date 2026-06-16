package com.hamburgueria.cardapio;

import java.math.BigDecimal;

public abstract class Produto implements ItemCardapio {

    private final String nome;
    private final BigDecimal preco;
    private final int calorias;

    protected Produto(String nome, BigDecimal preco, int calorias) {
        this.nome = nome;
        this.preco = preco;
        this.calorias = calorias;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public int getCalorias() {
        return calorias;
    }

    @Override
    public String getDescricao() {
        return nome;
    }
}
