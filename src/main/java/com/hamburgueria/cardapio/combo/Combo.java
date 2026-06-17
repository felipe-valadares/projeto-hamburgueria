package com.hamburgueria.cardapio.combo;

import com.hamburgueria.cardapio.Acompanhamento;
import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.cardapio.Produto;
import com.hamburgueria.cardapio.montagem.Hamburguer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Combo extends Produto {

    private static final BigDecimal FATOR_DESCONTO = new BigDecimal("0.90");

    private final Hamburguer lanche;
    private final Bebida bebida;
    private final Acompanhamento acompanhamento;

    public Combo(String nome, Hamburguer lanche, Bebida bebida, Acompanhamento acompanhamento) {
        super(nome, precoComDesconto(lanche, bebida, acompanhamento),
                lanche.getCalorias() + bebida.getCalorias() + acompanhamento.getCalorias());
        this.lanche = lanche;
        this.bebida = bebida;
        this.acompanhamento = acompanhamento;
    }

    public Hamburguer getLanche() {
        return lanche;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public Acompanhamento getAcompanhamento() {
        return acompanhamento;
    }

    @Override
    public String getDescricao() {
        return getNome() + ": " + lanche.getNome() + " + " + bebida.getDescricao()
                + " + " + acompanhamento.getNome();
    }

    private static BigDecimal precoComDesconto(Hamburguer lanche, Bebida bebida, Acompanhamento acompanhamento) {
        BigDecimal soma = lanche.getPreco().add(bebida.getPreco()).add(acompanhamento.getPreco());
        return soma.multiply(FATOR_DESCONTO).setScale(2, RoundingMode.HALF_UP);
    }
}
