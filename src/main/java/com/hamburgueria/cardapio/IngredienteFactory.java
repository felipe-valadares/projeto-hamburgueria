package com.hamburgueria.cardapio;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class IngredienteFactory {

    private static final Map<String, Ingrediente> CATALOGO = new HashMap<>();

    static {
        registrar("Pao Brioche", "1.80", 180, true);
        registrar("Pao Australiano", "2.20", 200, true);
        registrar("Hamburguer Bovino", "5.50", 250, false);
        registrar("Hamburguer Vegetal", "6.00", 190, true);
        registrar("Queijo Cheddar", "2.00", 110, false);
        registrar("Queijo Prato", "1.80", 90, false);
        registrar("Bacon", "3.00", 130, false);
        registrar("Ovo Frito", "2.00", 90, false);
        registrar("Alface", "0.50", 5, true);
        registrar("Tomate", "0.50", 8, true);
        registrar("Cebola Caramelizada", "1.20", 60, true);
        registrar("Molho Especial", "1.00", 70, true);
        registrar("Picles", "0.60", 10, true);
    }

    private IngredienteFactory() {
    }

    public static synchronized Ingrediente registrar(String nome, String preco, int calorias, boolean vegano) {
        Ingrediente ingrediente = new Ingrediente(nome, new BigDecimal(preco), calorias, vegano);
        CATALOGO.put(chave(nome), ingrediente);
        return ingrediente;
    }

    public static synchronized Ingrediente obter(String nome) {
        Ingrediente ingrediente = CATALOGO.get(chave(nome));
        if (ingrediente == null) {
            throw new IngredienteIndisponivelException(nome);
        }
        return ingrediente;
    }

    public static synchronized int quantidadeCadastrada() {
        return CATALOGO.size();
    }

    private static String chave(String nome) {
        return nome.trim().toLowerCase();
    }
}
