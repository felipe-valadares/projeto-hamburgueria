package com.hamburgueria.cardapio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categoria implements ItemCardapio {

    private final String nome;
    private final List<ItemCardapio> itens = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria adicionar(ItemCardapio item) {
        itens.add(item);
        return this;
    }

    public void remover(ItemCardapio item) {
        itens.remove(item);
    }

    public List<ItemCardapio> getItens() {
        return Collections.unmodifiableList(itens);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public BigDecimal getPreco() {
        return itens.stream()
                .map(ItemCardapio::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public int getCalorias() {
        return itens.stream().mapToInt(ItemCardapio::getCalorias).sum();
    }

    @Override
    public String getDescricao() {
        StringBuilder sb = new StringBuilder(nome);
        for (ItemCardapio item : itens) {
            sb.append("\n  - ").append(item.getDescricao());
        }
        return sb.toString();
    }
}
