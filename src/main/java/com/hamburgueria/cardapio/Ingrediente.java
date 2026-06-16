package com.hamburgueria.cardapio;

import java.math.BigDecimal;
import java.util.Objects;

public final class Ingrediente {

    private final String nome;
    private final BigDecimal preco;
    private final int calorias;
    private final boolean vegano;

    Ingrediente(String nome, BigDecimal preco, int calorias, boolean vegano) {
        this.nome = nome;
        this.preco = preco;
        this.calorias = calorias;
        this.vegano = vegano;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getCalorias() {
        return calorias;
    }

    public boolean isVegano() {
        return vegano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ingrediente)) {
            return false;
        }
        return nome.equalsIgnoreCase(((Ingrediente) o).nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    @Override
    public String toString() {
        return nome;
    }
}
