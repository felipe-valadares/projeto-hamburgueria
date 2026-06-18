package com.hamburgueria.pedido;

public class CarrinhoVazioException extends RuntimeException {

    public CarrinhoVazioException() {
        super("Nao e possivel fechar um pedido com o carrinho vazio.");
    }
}
