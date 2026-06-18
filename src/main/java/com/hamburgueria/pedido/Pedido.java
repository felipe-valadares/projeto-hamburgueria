package com.hamburgueria.pedido;

import com.hamburgueria.infraestrutura.GeradorId;
import com.hamburgueria.pedido.estado.EstadoPedido;
import com.hamburgueria.pedido.estado.PedidoRecebido;
import com.hamburgueria.pedido.evento.ObservadorPedido;
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
    private final List<ObservadorPedido> observadores = new ArrayList<>();
    private EstadoPedido estado = new PedidoRecebido();

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

    public StatusPedido getStatus() {
        return estado.getStatus();
    }

    public void avancar() {
        estado.avancar(this);
    }

    public void cancelar() {
        estado.cancelar(this);
    }

    public void transitarPara(EstadoPedido novoEstado) {
        this.estado = novoEstado;
        notificarObservadores();
    }

    public void registrarObservador(ObservadorPedido observador) {
        observadores.add(observador);
    }

    private void notificarObservadores() {
        for (ObservadorPedido observador : observadores) {
            observador.statusAtualizado(this);
        }
    }

    public Pedido repetir() {
        return clone();
    }

    @Override
    public Pedido clone() {
        return new Pedido(cliente, tipoEntrega, itens);
    }
}
