package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.IngredienteFactory;
import com.hamburgueria.cardapio.ItemCardapio;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdicionalDecoratorTest {

    private Hamburguer hamburguerBase() {
        return new HamburguerBuilder()
                .comNome("Smash")
                .comPao("Pao Brioche")
                .comProteina("Hamburguer Bovino")
                .construir();
    }

    @Test
    void adicionalAcrescentaPrecoAoItem() {
        Hamburguer base = hamburguerBase();
        ItemCardapio comBacon = new BaconExtra(base);

        BigDecimal esperado = base.getPreco().add(IngredienteFactory.obter("Bacon").getPreco());

        assertEquals(esperado, comBacon.getPreco());
    }

    @Test
    void adicionaisPodemSerEmpilhados() {
        Hamburguer base = hamburguerBase();
        ItemCardapio incrementado = new OvoExtra(new BaconExtra(base));

        BigDecimal esperado = base.getPreco()
                .add(IngredienteFactory.obter("Bacon").getPreco())
                .add(IngredienteFactory.obter("Ovo Frito").getPreco());

        assertEquals(esperado, incrementado.getPreco());
        assertTrue(incrementado.getDescricao().contains("Bacon"));
        assertTrue(incrementado.getDescricao().contains("Ovo Frito"));
    }

    @Test
    void adicionalAcrescentaCalorias() {
        Hamburguer base = hamburguerBase();
        ItemCardapio comCheddar = new CheddarExtra(base);

        assertEquals(base.getCalorias() + IngredienteFactory.obter("Queijo Cheddar").getCalorias(),
                comCheddar.getCalorias());
    }
}
