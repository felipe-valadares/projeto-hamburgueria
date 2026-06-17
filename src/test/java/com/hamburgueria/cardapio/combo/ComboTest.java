package com.hamburgueria.cardapio.combo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComboTest {

    @Test
    void montaFamiliaCoerenteParaPublicoInfantil() {
        Combo combo = new FabricaComboInfantil().montar();

        assertFalse(combo.getBebida().isAlcoolica());
        assertTrue(combo.getLanche().getNome().contains("Mini"));
    }

    @Test
    void comboAdultoPodeIncluirBebidaAlcoolica() {
        Combo combo = new FabricaComboAdulto().montar();

        assertTrue(combo.getBebida().isAlcoolica());
    }

    @Test
    void comboVeganoUsaProteinaVegetal() {
        Combo combo = new FabricaComboVegano().montar();

        assertTrue(combo.getLanche().getIngredientes().stream().allMatch(i -> i.isVegano()));
    }

    @Test
    void comboAplicaDescontoSobreOsItens() {
        Combo combo = new FabricaComboInfantil().montar();

        BigDecimal soma = combo.getLanche().getPreco()
                .add(combo.getBebida().getPreco())
                .add(combo.getAcompanhamento().getPreco());
        BigDecimal esperado = soma.multiply(new BigDecimal("0.90")).setScale(2, RoundingMode.HALF_UP);

        assertEquals(esperado, combo.getPreco());
    }
}
