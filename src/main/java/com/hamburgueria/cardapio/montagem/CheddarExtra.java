package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.IngredienteFactory;
import com.hamburgueria.cardapio.ItemCardapio;

public class CheddarExtra extends AdicionalDecorator {

    public CheddarExtra(ItemCardapio item) {
        super(item, IngredienteFactory.obter("Queijo Cheddar"));
    }
}
