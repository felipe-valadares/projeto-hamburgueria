package com.hamburgueria.cardapio;

import java.math.BigDecimal;

public interface ItemCardapio {

    String getNome();

    BigDecimal getPreco();

    int getCalorias();

    String getDescricao();
}
