package com.hamburgueria.operacao;

import com.hamburgueria.cardapio.montagem.BaconExtra;
import com.hamburgueria.cardapio.montagem.HamburguerBuilder;
import com.hamburgueria.notificacao.CanalWhatsApp;
import com.hamburgueria.pagamento.PagamentoDinheiro;
import com.hamburgueria.pagamento.StatusPagamento;
import com.hamburgueria.pagamento.gateway.GatewayPagamentoAdapter;
import com.hamburgueria.pagamento.gateway.GatewayPagamentosExterno;
import com.hamburgueria.pedido.Carrinho;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.StatusPedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import com.hamburgueria.pessoa.Endereco;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtendimentoFacadeTest {

    private Cliente clienteComEndereco() {
        Cliente cliente = new Cliente("Bruno", "11988887777", "bruno@email.com");
        cliente.setEndereco(new Endereco("Rua das Flores", "200", "Jardim", 3.0));
        return cliente;
    }

    private Carrinho carrinhoComLanche() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new ItemPedido(new BaconExtra(new HamburguerBuilder()
                .comNome("Smash").comPao("Pao Brioche").comProteina("Hamburguer Bovino").construir()), 1));
        return carrinho;
    }

    @Test
    void realizaPedidoDeliveryDoInicioAoFimEmUmaUnicaChamada() {
        AtendimentoFacade atendimento = new AtendimentoFacade(new CanalWhatsApp());
        Carrinho carrinho = carrinhoComLanche();

        ResumoAtendimento resumo = atendimento.realizarPedido(clienteComEndereco(), carrinho,
                TipoEntrega.DELIVERY, new PagamentoDinheiro(new BigDecimal("100.00")));

        assertEquals(StatusPagamento.PAGO, resumo.getStatusPagamento());
        assertEquals(StatusPedido.SAIU_PARA_ENTREGA, resumo.getStatusPedido());
        assertEquals(resumo.getSubtotal().add(resumo.getFrete()), resumo.getTotal());
        assertFalse(resumo.getAvisos().isEmpty());
    }

    @Test
    void pagamentoRecusadoNaoColocaPedidoEmProducao() {
        AtendimentoFacade atendimento = new AtendimentoFacade(new CanalWhatsApp());
        Carrinho carrinho = carrinhoComLanche();

        ResumoAtendimento resumo = atendimento.realizarPedido(clienteComEndereco(), carrinho,
                TipoEntrega.DELIVERY,
                new GatewayPagamentoAdapter(new GatewayPagamentosExterno(), "Elo", "6000000000000000"));

        assertEquals(StatusPagamento.RECUSADO, resumo.getStatusPagamento());
        assertEquals(StatusPedido.RECEBIDO, resumo.getStatusPedido());
        assertTrue(resumo.getAvisos().isEmpty());
    }
}
