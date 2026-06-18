package com.hamburgueria.pedido;

import com.hamburgueria.infraestrutura.GeradorId;
import com.hamburgueria.pessoa.Cliente;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido implements Cloneable {

    private final long id;
    private final Cliente cliente;
    private final TipoEntrega tipoEntrega;
    private final List<ItemPedido> itens;
    private final LocalDateTime momento;

    public Pedido(Cliente cliente, TipoEntrega tipoEntrega, List<ItemPedido> itens) {
        this.id = GeradorId.proximo();
        this.cliente = cliente;
        this.tipoEntrega = tipoEntrega;
        this.itens = new ArrayList<>();
        for (ItemPedido item : itens) {
            this.itens.add(item.copiar());
        }
        this.momento = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public List<ItemPedido> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public int quantidadeItens() {
        return itens.stream().mapToInt(ItemPedido::getQuantidade).sum();
    }

    public LocalDateTime getMomento() {
        return momento;
    }

    public BigDecimal getSubtotal() {
        return itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Pedido repetir() {
        return clone();
    }

    @Override
    public Pedido clone() {
        return new Pedido(cliente, tipoEntrega, itens);
    }
}
