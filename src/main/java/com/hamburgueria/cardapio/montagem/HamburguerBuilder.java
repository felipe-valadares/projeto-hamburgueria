package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.Ingrediente;
import com.hamburgueria.cardapio.IngredienteFactory;

import java.util.ArrayList;
import java.util.List;

public class HamburguerBuilder {

    private String nome = "Hamburguer da Casa";
    private PontoCarne ponto = PontoCarne.AO_PONTO;
    private final List<Ingrediente> ingredientes = new ArrayList<>();
    private boolean temPao;
    private boolean temProteina;

    public HamburguerBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public HamburguerBuilder comPao(String pao) {
        ingredientes.add(IngredienteFactory.obter(pao));
        temPao = true;
        return this;
    }

    public HamburguerBuilder comProteina(String proteina) {
        ingredientes.add(IngredienteFactory.obter(proteina));
        temProteina = true;
        return this;
    }

    public HamburguerBuilder noPonto(PontoCarne ponto) {
        this.ponto = ponto;
        return this;
    }

    public HamburguerBuilder adicionar(String ingrediente) {
        ingredientes.add(IngredienteFactory.obter(ingrediente));
        return this;
    }

    public Hamburguer construir() {
        if (!temPao) {
            throw new MontagemInvalidaException("Um hamburguer precisa de pao.");
        }
        if (!temProteina) {
            throw new MontagemInvalidaException("Um hamburguer precisa de ao menos uma proteina.");
        }
        return new Hamburguer(nome, ponto, ingredientes);
    }
}
