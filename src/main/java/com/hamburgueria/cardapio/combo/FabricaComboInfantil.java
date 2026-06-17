package com.hamburgueria.cardapio.combo;

import com.hamburgueria.cardapio.Acompanhamento;
import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.montagem.Hamburguer;
import com.hamburgueria.cardapio.montagem.HamburguerBuilder;
import com.hamburgueria.cardapio.montagem.PontoCarne;

import java.math.BigDecimal;

public class FabricaComboInfantil implements FabricaCombo {

    @Override
    public String nomeCombo() {
        return "Combo Infantil";
    }

    @Override
    public Hamburguer criarLanche() {
        return new HamburguerBuilder()
                .comNome("Mini Burger")
                .comPao("Pao Brioche")
                .comProteina("Hamburguer Bovino")
                .noPonto(PontoCarne.BEM_PASSADO)
                .construir();
    }

    @Override
    public Bebida criarBebida() {
        return new Bebida("Suco de Uva", new BigDecimal("6.00"), 80, 200, false);
    }

    @Override
    public Acompanhamento criarAcompanhamento() {
        return new Acompanhamento("Batata Pequena", new BigDecimal("8.00"), 250);
    }
}
