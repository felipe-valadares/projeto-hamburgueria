package com.hamburgueria.cardapio.montagem;

import com.hamburgueria.cardapio.Ingrediente;
import com.hamburgueria.cardapio.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class Hamburguer extends Produto {

    private final PontoCarne ponto;
    private final List<Ingrediente> ingredientes;

    Hamburguer(String nome, PontoCarne ponto, List<Ingrediente> ingredientes) {
        super(nome, calcularPreco(ingredientes), calcularCalorias(ingredientes));
        this.ponto = ponto;
        this.ingredientes = List.copyOf(ingredientes);
    }

    public PontoCarne getPonto() {
        return ponto;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    @Override
    public String getDescricao() {
        String composicao = ingredientes.stream()
                .map(Ingrediente::getNome)
                .collect(Collectors.joining(", "));
        if (ponto == PontoCarne.NAO_SE_APLICA) {
            return getNome() + " (" + composicao + ")";
        }
        return getNome() + " (" + composicao + "; carne " + nomearPonto() + ")";
    }

    private String nomearPonto() {
        return ponto.name().toLowerCase().replace('_', ' ');
    }

    private static BigDecimal calcularPreco(List<Ingrediente> ingredientes) {
        return ingredientes.stream()
                .map(Ingrediente::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static int calcularCalorias(List<Ingrediente> ingredientes) {
        return ingredientes.stream().mapToInt(Ingrediente::getCalorias).sum();
    }
}
