package com.hamburgueria.entrega.frete;

import com.hamburgueria.cardapio.Bebida;
import com.hamburgueria.infraestrutura.Configuracao;
import com.hamburgueria.pedido.ItemPedido;
import com.hamburgueria.pedido.Pedido;
import com.hamburgueria.pedido.TipoEntrega;
import com.hamburgueria.pessoa.Cliente;
import com.hamburgueria.pessoa.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculoFreteTest {

    @BeforeEach
    void restaurarConfiguracao() {
        Configuracao config = Configuracao.getInstancia();
        config.setFreteBase(new BigDecimal("6.00"));
        config.setValorPorQuilometro(new BigDecimal("1.50"));
        config.setPedidoMinimoFreteGratis(new BigDecimal("80.00"));
    }

    private Pedido pedido(double distanciaKm, String valorItem) {
        Cliente cliente = new Cliente("Ana", "11999990000", "ana@email.com");
        cliente.setEndereco(new Endereco("Rua A", "100", "Centro", distanciaKm));
        ItemPedido item = new ItemPedido(new Bebida("Bebida", new BigDecimal(valorItem), 100, 350, false), 1);
        return new Pedido(cliente, TipoEntrega.DELIVERY, List.of(item));
    }

    @Test
    void fretePorDistanciaSomaBaseMaisValorPorQuilometro() {
        BigDecimal frete = new FretePorDistancia().calcular(pedido(4.0, "20.00"));

        assertEquals(new BigDecimal("12.00"), frete);
    }

    @Test
    void freteEhGratuitoAcimaDoPedidoMinimo() {
        CalculoFrete estrategia = new FreteGratisAcimaDe(new FretePorDistancia());

        BigDecimal frete = estrategia.calcular(pedido(4.0, "90.00"));

        assertEquals(new BigDecimal("0.00"), frete);
    }

    @Test
    void abaixoDoMinimoCobraOfreteDaEstrategiaBase() {
        CalculoFrete estrategia = new FreteGratisAcimaDe(new FretePorDistancia());

        BigDecimal frete = estrategia.calcular(pedido(4.0, "30.00"));

        assertEquals(new BigDecimal("12.00"), frete);
    }
}
