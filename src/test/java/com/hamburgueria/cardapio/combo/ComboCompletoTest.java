package com.hamburgueria.cardapio.combo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ComboCompletoTest {

    @Test
    void descricaoListaOsComponentes() {
        Combo combo = new FabricaComboAdulto().montar();
        String descricao = combo.getDescricao();
        assertNotNull(descricao);
        assertTrue(descricao.contains("Master Burger"));
    }

    @Test
    void nomeDoComboEhRetornado() {
        Combo combo = new FabricaComboVegano().montar();
        assertTrue(combo.getNome().contains("Verde"));
    }
}
