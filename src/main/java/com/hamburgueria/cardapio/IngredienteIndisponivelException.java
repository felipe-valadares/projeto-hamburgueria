package com.hamburgueria.cardapio;

public class IngredienteIndisponivelException extends RuntimeException {

    public IngredienteIndisponivelException(String nome) {
        super("Ingrediente nao disponivel no estoque: " + nome);
    }
}
