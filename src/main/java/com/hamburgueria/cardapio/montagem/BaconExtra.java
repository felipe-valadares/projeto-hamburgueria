package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.IngredienteFactory;
import com.hamburgueria.cardapio.ItemCardapio;

public class BaconExtra extends AdicionalDecorator {

    public BaconExtra(ItemCardapio item) {
        super(item, IngredienteFactory.obter("Bacon"));
    }
}
