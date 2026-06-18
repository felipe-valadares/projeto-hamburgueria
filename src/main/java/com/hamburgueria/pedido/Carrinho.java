package com.hamburgueria.pedido;

import com.hamburgueria.pessoa.Cliente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho {

    private final List<ItemPedido> itens = new ArrayList<>();

    public void adicionar(ItemPedido item) {
        itens.add(item);
    }

    public void remover(ItemPedido item) {
        itens.remove(item);
    }

    public void limpar() {
        itens.clear();
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public int quantidadeItens() {
        return itens.stream().mapToInt(ItemPedido::getQuantidade).sum();
    }

    public boolean isVazio() {
        return itens.isEmpty();
    }

    public BigDecimal getSubtotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Pedido fecharPedido(Cliente cliente, TipoEntrega tipoEntrega) {
        if (isVazio()) {
            throw new CarrinhoVazioException();
        }
        return new Pedido(cliente, tipoEntrega, itens);
    }
}
