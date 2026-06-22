package com.hamburgueria.relatorio;

import com.hamburgueria.pessoa.Cliente;
import com.hamburgueria.pessoa.Fornecedor;
import com.hamburgueria.pessoa.Funcionario;
import com.hamburgueria.pessoa.VisitantePessoa;

import java.math.BigDecimal;

public class RelatorioFolhaPagamento implements VisitantePessoa {

    private BigDecimal total = BigDecimal.ZERO;
    private int funcionarios;

    @Override
    public void visitar(Cliente cliente) {
        // clientes nao compoem a folha de pagamento
    }

    @Override
    public void visitar(Funcionario funcionario) {
        total = total.add(funcionario.getSalario());
        funcionarios++;
    }

    @Override
    public void visitar(Fornecedor fornecedor) {
        // fornecedores sao pagos por nota, fora da folha
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getFuncionarios() {
        return funcionarios;
    }
}
