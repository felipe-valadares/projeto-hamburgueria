package com.hamburgueria.cardapio.combo;

import com.hamburgueria.cardapio.Acompanhamento;
import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.montagem.Hamburguer;

public interface FabricaCombo {

    String nomeCombo();

    Hamburguer criarLanche();

    Bebida criarBebida();

    Acompanhamento criarAcompanhamento();

    default Combo montar() {
        return new Combo(nomeCombo(), criarLanche(), criarBebida(), criarAcompanhamento());
    }
}
