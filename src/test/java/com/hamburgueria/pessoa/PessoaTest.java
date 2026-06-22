package com.hamburgueria.pessoa;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PessoaTest {

    @Test
    void enderecoToString() {
        Endereco e = new Endereco("Rua A", "100", "Centro", 3.0);
        assertEquals("Rua A, 100 - Centro", e.toString());
        assertEquals("Rua A", e.getLogradouro());
        assertEquals("100", e.getNumero());
        assertEquals("Centro", e.getBairro());
    }

    @Test
    void funcionarioAcessores() {
        Funcionario f = new Funcionario("Carlos", "Cozinheiro", "F-01", new BigDecimal("2500.00"));
        assertEquals("Carlos", f.getNome());
        assertEquals("Cozinheiro", f.getCargo());
        assertEquals("F-01", f.getMatricula());
        assertEquals(new BigDecimal("2500.00"), f.getSalario());
    }

    @Test
    void fornecedorAcessores() {
        Fornecedor f = new Fornecedor("Boi Bravo", "12345", "Carne");
        assertEquals("Boi Bravo", f.getNome());
        assertEquals("12345", f.getCnpj());
        assertEquals("Carne", f.getInsumoFornecido());
    }
}
