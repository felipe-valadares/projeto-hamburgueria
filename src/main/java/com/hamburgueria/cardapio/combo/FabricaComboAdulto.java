package com.hamburgueria.cardapio.combo;

import com.hamburgueria.cardapio.Acompanhamento;
import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.montagem.Hamburguer;
import com.hamburgueria.cardapio.montagem.HamburguerBuilder;
import com.hamburgueria.cardapio.montagem.PontoCarne;

import java.math.BigDecimal;

public class FabricaComboAdulto implements FabricaCombo {

    @Override
    public String nomeCombo() {
        return "Combo Master";
    }

    @Override
    public Hamburguer criarLanche() {
        return new HamburguerBuilder()
                .comNome("Master Burger")
                .comPao("Pao Australiano")
                .comProteina("Hamburguer Bovino")
                .adicionar("Queijo Cheddar")
                .adicionar("Bacon")
                .adicionar("Cebola Caramelizada")
                .noPonto(PontoCarne.AO_PONTO)
                .construir();
    }

    @Override
    public Bebida criarBebida() {
        return new Bebida("Cerveja Artesanal", new BigDecimal("14.00"), 150, 350, true);
    }

    @Override
    public Acompanhamento criarAcompanhamento() {
        return new Acompanhamento("Onion Rings", new BigDecimal("16.00"), 480);
    }
}
