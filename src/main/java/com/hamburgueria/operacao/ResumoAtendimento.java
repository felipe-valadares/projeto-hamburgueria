package com.hamburgueria.operacao;

import com.hamburgueria.pagamento.StatusPagamento;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.StatusPedido;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class ResumoAtendimento {

    private final long pedidoId;
    private final BigDecimal subtotal;
    private final BigDecimal frete;
    private final BigDecimal total;
    private final StatusPagamento statusPagamento;
    private final StatusPedido statusPedido;
    private final List<String> avisos;

    public ResumoAtendimento(Pedido pedido, BigDecimal frete, BigDecimal total,
                             StatusPagamento statusPagamento, List<String> avisos) {
        this.pedidoId = pedido.getId();
        this.subtotal = pedido.getSubtotal();
        this.frete = frete;
        this.total = total;
        this.statusPagamento = statusPagamento;
        this.statusPedido = pedido.getStatus();
        this.avisos = avisos;
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public List<String> getAvisos() {
        return Collections.unmodifiableList(avisos);
    }
}
