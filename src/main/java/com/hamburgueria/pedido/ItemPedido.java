package com.hamburgueria.pedido;

import com.hamburgueria.cardapio.ItemCardapio;

import java.math.BigDecimal;

public class ItemPedido {

    private final ItemCardapio produto;
    private int quantidade;

    public ItemPedido(ItemCardapio produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemCardapio getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser positiva.");
        }
        this.quantidade = quantidade;
    }

    public BigDecimal getSubtotal() {
        return produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public ItemPedido copiar() {
        return new ItemPedido(produto, quantidade);
    }
}
