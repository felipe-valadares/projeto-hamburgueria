package com.hamburgueria.cardapio.combo;

import com.hamburgueria.cardapio.Acompanhamento;
import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.montagem.Hamburguer;
import com.hamburgueria.cardapio.montagem.HamburguerBuilder;
import com.hamburgueria.cardapio.montagem.PontoCarne;

import java.math.BigDecimal;

public class FabricaComboVegano implements FabricaCombo {

    @Override
    public String nomeCombo() {
        return "Combo Verde";
    }

    @Override
    public Hamburguer criarLanche() {
        return new HamburguerBuilder()
                .comNome("Green Burger")
                .comPao("Pao Brioche")
                .comProteina("Hamburguer Vegetal")
                .adicionar("Alface")
                .adicionar("Tomate")
                .noPonto(PontoCarne.NAO_SE_APLICA)
                .construir();
    }

    @Override
    public Bebida criarBebida() {
        return new Bebida("Suco Natural Detox", new BigDecimal("11.00"), 70, 400, false);
    }

    @Override
    public Acompanhamento criarAcompanhamento() {
        return new Acompanhamento("Batata Rustica", new BigDecimal("13.00"), 300);
    }
}
