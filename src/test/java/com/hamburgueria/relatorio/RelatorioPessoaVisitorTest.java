package com.hamburgueria.relatorio;

import com.hamburgueria.pessoa.Cliente;
import com.hamburgueria.pessoa.Fornecedor;
import com.hamburgueria.pessoa.Funcionario;
import com.hamburgueria.pessoa.Pessoa;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RelatorioPessoaVisitorTest {

    private List<Pessoa> cadastro() {
        return List.of(
                new Cliente("Ana", "11999990000", "ana@email.com"),
                new Funcionario("Carlos", "Cozinheiro", "F-01", new BigDecimal("2500.00")),
                new Funcionario("Diana", "Gerente", "F-02", new BigDecimal("4500.00")),
                new Fornecedor("Frigorifico Boi Bravo", "12.345.678/0001-90", "Carne bovina"));
    }

    @Test
    void relatorioDeContatosFormataCadaTipoDeForma() {
        RelatorioContatos relatorio = new RelatorioContatos();

        cadastro().forEach(pessoa -> pessoa.aceitar(relatorio));

        assertEquals(4, relatorio.getLinhas().size());
        assertTrue(relatorio.getLinhas().get(0).startsWith("Cliente:"));
        assertTrue(relatorio.getLinhas().get(1).startsWith("Funcionario:"));
        assertTrue(relatorio.getLinhas().get(3).startsWith("Fornecedor:"));
    }

    @Test
    void folhaDePagamentoSomaApenasSalariosDeFuncionarios() {
        RelatorioFolhaPagamento folha = new RelatorioFolhaPagamento();

        cadastro().forEach(pessoa -> pessoa.aceitar(folha));

        assertEquals(2, folha.getFuncionarios());
        assertEquals(new BigDecimal("7000.00"), folha.getTotal());
    }
}
