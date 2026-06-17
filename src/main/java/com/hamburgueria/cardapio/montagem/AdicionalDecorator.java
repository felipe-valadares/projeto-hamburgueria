package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.Ingrediente;
import com.hamburgueria.cardapio.ItemCardapio;

import java.math.BigDecimal;

public abstract class AdicionalDecorator implements ItemCardapio {

    private final ItemCardapio item;
    private final Ingrediente adicional;

    protected AdicionalDecorator(ItemCardapio item, Ingrediente adicional) {
        this.item = item;
        this.adicional = adicional;
    }

    @Override
    public String getNome() {
        return item.getNome();
    }

    @Override
    public BigDecimal getPreco() {
        return item.getPreco().add(adicional.getPreco());
    }

    @Override
    public int getCalorias() {
        return item.getCalorias() + adicional.getCalorias();
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + " + adicional.getNome();
    }
}
