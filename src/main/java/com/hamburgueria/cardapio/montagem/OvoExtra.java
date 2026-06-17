package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.IngredienteFactory;
import com.hamburgueria.cardapio.ItemCardapio;

public class OvoExtra extends AdicionalDecorator {

    public OvoExtra(ItemCardapio item) {
        super(item, IngredienteFactory.obter("Ovo Frito"));
    }
}
