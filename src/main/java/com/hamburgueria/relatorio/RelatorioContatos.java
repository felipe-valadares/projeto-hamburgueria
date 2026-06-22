package com.hamburgueria.relatorio;

import com.hamburgueria.pessoa.Cliente;
import com.hamburgueria.pessoa.Fornecedor;
import com.hamburgueria.pessoa.Funcionario;
import com.hamburgueria.pessoa.VisitantePessoa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RelatorioContatos implements VisitantePessoa {

    private final List<String> linhas = new ArrayList<>();

    @Override
    public void visitar(Cliente cliente) {
        linhas.add("Cliente: " + cliente.getNome() + " - tel " + cliente.getTelefone());
    }

    @Override
    public void visitar(Funcionario funcionario) {
        linhas.add("Funcionario: " + funcionario.getNome() + " - " + funcionario.getCargo()
                + " (matricula " + funcionario.getMatricula() + ")");
    }

    @Override
    public void visitar(Fornecedor fornecedor) {
        linhas.add("Fornecedor: " + fornecedor.getNome() + " - " + fornecedor.getInsumoFornecido()
                + " (CNPJ " + fornecedor.getCnpj() + ")");
    }

    public List<String> getLinhas() {
        return Collections.unmodifiableList(linhas);
    }
}
