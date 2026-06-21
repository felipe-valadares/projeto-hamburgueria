package com.hamburgueria.operacao;

import com.hamburgueria.entrega.ServicoEntrega;
import com.hamburgueria.entrega.ServicoEntregaFactory;
import com.hamburgueria.notificacao.CanalEnvio;
import com.hamburgueria.pagamento.MeioPagamento;
import com.hamburgueria.pagamento.Pagamento;
import com.hamburgueria.pagamento.StatusPagamento;
import com.hamburgueria.pedido.Carrinho;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;

import java.math.BigDecimal;
import java.util.List;

public class AtendimentoFacade {

    private final ServicoEntregaFactory entregaFactory = new ServicoEntregaFactory();
    private final CanalEnvio canal;

    public AtendimentoFacade(CanalEnvio canal) {
        this.canal = canal;
    }

    public ResumoAtendimento realizarPedido(Cliente cliente, Carrinho carrinho,
                                            TipoEntrega tipoEntrega, MeioPagamento meioPagamento) {
        Pedido pedido = carrinho.fecharPedido(cliente, tipoEntrega);
        ServicoEntrega servico = entregaFactory.criar(tipoEntrega);
        BigDecimal frete = servico.calcularFrete(pedido);
        BigDecimal total = pedido.getSubtotal().add(frete);

        Pagamento pagamento = new Pagamento(total, meioPagamento);
        pagamento.processar();

        if (pagamento.getStatus() != StatusPagamento.PAGO) {
            return new ResumoAtendimento(pedido, frete, total, pagamento.getStatus(), List.of());
        }

        CentralOperacoes central = new CentralOperacoes(canal);
        central.processar(pedido);

        return new ResumoAtendimento(pedido, frete, total, pagamento.getStatus(), central.getAvisos());
    }
}
